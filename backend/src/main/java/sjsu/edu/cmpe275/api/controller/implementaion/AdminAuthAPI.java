package sjsu.edu.cmpe275.api.controller.implementaion;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.OHConstants;
import sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI;
import sjsu.edu.cmpe275.api.persistence.model.Expense;
import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.persistence.model.mapper.HackathonToHackathonReponseMapper;
import sjsu.edu.cmpe275.api.resources.FinancialReport;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;
import sjsu.edu.cmpe275.api.resources.LeaderBoardTeam;
import sjsu.edu.cmpe275.api.resources.MemberDetail;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;

@Controller
public class AdminAuthAPI implements IAdminAuthAPI {

	@Autowired
	private IHackathonManagementService hackathonManagementService;

	@Autowired
	private HackathonToHackathonReponseMapper hackathonToHackathonReponseMapper;

	@Override
	public ResponseEntity<Object> createUpdateHackathon(String token, HackathonRequest hackathonRequest)
			throws ParseException {
		Hackathon hackathon = hackathonManagementService.createOrUpdateHackathon(hackathonRequest);
		HackathonResponse response = hackathonToHackathonReponseMapper.map(hackathon, true, true, false, null);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> financialReport(String token, String email,  String eventName) throws ParseException {
		Map<String, List<HackathonTeamProfile>> teams = hackathonManagementService.retrieveTeamsInHackathon(eventName);
		Hackathon hackathon = hackathonManagementService.retrieveHackathonDetail(email, OHConstants.ADMIN_ROLE , eventName);
		FinancialReport response = new FinancialReport();
		response.setEventName(eventName);
		float total = 0f;
		int unpaid = 0;
		for (Entry<String, List<HackathonTeamProfile>> teamEntry : teams.entrySet()) {
			LeaderBoardTeam team = new LeaderBoardTeam();
			response.getTeams().add(team);
			team.setAllPaid(true);
			team.setTeamName(teamEntry.getKey());
			for (HackathonTeamProfile profile : teamEntry.getValue()) {
				MemberDetail memberDetail = new MemberDetail();
				String[] split = profile.getHackathonTeamProfile().split(OHConstants.DELIMIT_HACK_TEAM_PROFILE);
				
				memberDetail.setEmail(split[2]);
				memberDetail.setAmountPaid(profile.getAmountPaid());
				memberDetail.setPaid(profile.isPaid());
				if (!profile.isPaid()) {
					unpaid++;
					team.setAllPaid(false);
				} else {
					total += profile.getAmountPaid();
				}
				memberDetail.setTimeOfPayment(profile.getTimeOfPayment());
				team.getTeamMembers().add(memberDetail);
			}
		}
		response.setAmountUnpaid(hackathon.getRegistrationFee()*unpaid);
		response.setRevenueFromRegistration(total);
		response.setRevenueFromSponsor(hackathon.getSponsors().size()*1000f);
		float exp = 0f;
		for(Expense expense :hackathon.getExpenses()) {
			sjsu.edu.cmpe275.api.resources.Expense resExpense = new sjsu.edu.cmpe275.api.resources.Expense();
			resExpense.setAmount(expense.getAmount());
			resExpense.setDescription(expense.getDescription());
			resExpense.setTimeOfExpense(expense.getTimeOfExpense());
			resExpense.setTitle(expense.getTitle());
			response.getExpenses().add(resExpense);
			exp+=expense.getAmount();
		}
		response.setTotalExpenses(exp);
		response.setProfit(total+response.getRevenueFromSponsor()-exp);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}

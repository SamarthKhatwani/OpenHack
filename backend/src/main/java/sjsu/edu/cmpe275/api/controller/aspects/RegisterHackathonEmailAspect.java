package sjsu.edu.cmpe275.api.controller.aspects;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.persistence.model.mapper.LeaderBoardResponseMapper;
import sjsu.edu.cmpe275.api.persistence.repository.HacathonTeamProfileRepository;
import sjsu.edu.cmpe275.api.persistence.repository.HackathonRepository;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;
import sjsu.edu.cmpe275.api.resources.LeaderBoardResponse;
import sjsu.edu.cmpe275.api.resources.LeaderBoardTeam;
import sjsu.edu.cmpe275.api.resources.MemberDetail;
import sjsu.edu.cmpe275.api.resources.Quotation;
import sjsu.edu.cmpe275.api.resources.TeamMemberRequest;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;
import sjsu.edu.cmpe275.api.service.implementation.HackathonManagementService;

@Aspect
@Order(7)
@Configuration
public class RegisterHackathonEmailAspect {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${register.email.subject}")
	private String subject;

	@Value("${register.email.content}")
	private String content;

	@Value("${register.email.page}")
	private String pay;

	@Value("${frontend.leader.board}")
	private String leaderBoard;

	@Value("${frontend.end.point}")
	private String base;

	@Autowired
	private HacathonTeamProfileRepository hacathonTeamProfileRepository;

	@Autowired
	private HackathonManagementService hackathonManagementService;

	@Autowired
	private LeaderBoardResponseMapper leaderBoardResponseMapper;
	
	@Autowired
	private HackathonRepository hackathonRepository;

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI.registerHackathon(..))", returning = "response")
	public ResponseEntity<Object> sendEmailAspect(JoinPoint joinPoint, ResponseEntity<Object> response) {
		Object[] args = joinPoint.getArgs();
		TeamRegisterRequest request = (TeamRegisterRequest) args[1];
		for (TeamMemberRequest member : request.getTeamMembers()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(member.getEmail());
			message.setSubject(request.getEmail() + subject.replace("\"", "") + request.getEventName());
			String[] splits = content.split("#");
			message.setText(splits[0].replace("\"", "") + request.getEventName() + splits[1].replace("\"", "") + base
					+ pay + "?email=" + member.getEmail() + "&eventName=" + request.getEventName());
			javaMailSender.send(message);
		}
		return response;
	}

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI.makePayment(..))", returning = "response")
	public ResponseEntity<Object> sendEmailAllRegisterdAspect(JoinPoint joinPoint, ResponseEntity<Object> response) {
		Object[] args = joinPoint.getArgs();
		Quotation request = (Quotation) args[1];
		List<HackathonTeamProfile> profiles = hacathonTeamProfileRepository.findByTeamName(request.getTeamName());
		String to = "";
		boolean allPaid = true;
		for (HackathonTeamProfile profile : profiles) {
			if (!profile.isPaid()) {
				allPaid = false;
				break;
			}
			if (profile.isLead()) {
				to = profile.getProfile().getEmail();
			}
		}
		if (allPaid && !to.isEmpty()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject("Everyone in you team: " + request.getTeamName() + " paid the fee for event: "
					+ request.getEventName() + ", Enjoy hacking");
			message.setText("Everyone in you team: " + request.getTeamName() + " paid the fee for event: "
					+ request.getEventName() + ", Enjoy hacking");
			javaMailSender.send(message);
		}
		return response;
	}

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI.createUpdateHackathon(..))", returning = "response")
	public ResponseEntity<Object> sendLeaderBoardEmail(JoinPoint joinPoint, ResponseEntity<Object> response) {
		HackathonResponse hack = (HackathonResponse) response.getBody();
		if (hack.isFinalized()) {
			String url = base + leaderBoard + "?eventName=" + hack.getEventName();
			SortedMap<Float, Map<String, List<HackathonTeamProfile>>> teamsByScore = hackathonManagementService
					.retrieveLeaderBoardTeams(hack.getEventName());
			LeaderBoardResponse leaderBoardResponse = leaderBoardResponseMapper.mapLeaderBoardResponse(teamsByScore);
			for (LeaderBoardTeam team : leaderBoardResponse.getResult()) {
				String subject = "";
				switch (team.getRank()) {
				case 1:
					subject = "Congratulations!! your team " + team.getTeamName()
							+ " has secured 1st position in the \"" + hack.getEventName() + "\" event";
					break;
				case 2:
					subject = "Congratulations!! your team " + team.getTeamName()
							+ " has secured 2nd position in the \"" + hack.getEventName() + "\" event";
					break;
				case 3:
					subject = "Congratulations!! your team " + team.getTeamName()
							+ " has secured 3rd position in the \"" + hack.getEventName() + "\" event";
					break;
				default:
					subject = "Leader board is ready for " + hack.getEventName();
				}
				String body = subject + "\nclick the link below to see leader board \n" + url;
				
				for(MemberDetail member: team.getTeamMembers()) {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(member.getEmail());
					message.setSubject(subject);
					message.setText(body);
					javaMailSender.send(message);
				}
			}
			List<String> judges = hackathonRepository.findJudgeByHackathon(hack.getEventName());
			for(String judge: judges) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(judge);
				message.setSubject("Leader board is ready for " + hack.getEventName());
				message.setText(subject + "\nclick the link below to see leader board \n" + url);
				javaMailSender.send(message);
			}
			
		}
		
		return response;
	}
}

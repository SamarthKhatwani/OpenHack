package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.OHConstants;
import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.persistence.repository.HacathonTeamProfileRepository;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;
import sjsu.edu.cmpe275.api.resources.TeamDetailResponse;
import sjsu.edu.cmpe275.api.resources.TeamMemberRequest;

@Component
public class HackathonToHackathonReponseMapper {

	@Autowired
	private HacathonTeamProfileRepository hacathonTeamProfileRepository;

	public HackathonResponse map(Hackathon hackathon, boolean mapJudge, boolean mapSponsor, boolean mapTeam,
			String email) throws ParseException {
		HackathonResponse response = new HackathonResponse();
		response.setDescription(hackathon.getDescription());
		response.setDiscount(hackathon.getDiscount());
		response.setEndDate(hackathon.getEndDate());
		response.setEventName(hackathon.getEventName());
		response.setFinalized(hackathon.isFinalized());
		response.setRegistrationFee(hackathon.getRegistrationFee());
		response.setStartDate(hackathon.getStartDate());
		response.setTeamMaxSize(hackathon.getTeamMaxSize());
		response.setTeamMinSize(hackathon.getTeamMinSize());
		response.setOpen(hackathon.isOpen());
		if (mapJudge) {
			response.setJudges(hackathon.getJudges().stream().map(h -> h.getEmail()).collect(Collectors.toList()));
		}
		if (mapSponsor) {
			response.setSponsors(hackathon.getSponsors().stream().map(h -> h.getName()).collect(Collectors.toList()));
		}
		if (mapTeam) {
			Map<String, TeamDetailResponse> team = new HashMap<>();
			response.setTeam(team);
			Map<String, List<HackathonTeamProfile>> teams = hackathon.getTeams().stream()
					.collect(Collectors.groupingBy(hack -> hack.getTeamName()));
			if (email != null) {
				for (Entry<String, List<HackathonTeamProfile>> teamEntry : teams.entrySet()) {
					List<HackathonTeamProfile> list = teamEntry.getValue();
					TeamDetailResponse teamMemDetail = new TeamDetailResponse();
					boolean teamFound =false;
					boolean isAllPaid = true;
					for(HackathonTeamProfile teamProfile: list) {
						String[] splits = teamProfile.getHackathonTeamProfile().split(OHConstants.DELIMIT_HACK_TEAM_PROFILE);
						if(splits[2].equals(email)) {
							teamFound = true;
						}
						teamMemDetail.getMember().add(splits[2]);
						if(teamProfile.getSubmission()!=null) {
							teamMemDetail.setSubmission(teamProfile.getSubmission());
						}
						if(!teamProfile.isPaid()) {
							isAllPaid=false;
						}
					}
					if(teamFound) {
						teamMemDetail.setAllPaid(isAllPaid);
						team.put(teamEntry.getKey(), teamMemDetail);
						break;
					}
				}

			}
		}
		response.setSuccess(true);
		response.setMessage("Successful");
		return response;
	}
}

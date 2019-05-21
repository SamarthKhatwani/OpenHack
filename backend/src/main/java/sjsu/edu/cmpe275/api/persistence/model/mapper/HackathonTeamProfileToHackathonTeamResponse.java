package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.resources.HacakthonTeamResponse;
import sjsu.edu.cmpe275.api.resources.TeamProfileResponse;

@Component
public class HackathonTeamProfileToHackathonTeamResponse {

	public HacakthonTeamResponse map(List<HackathonTeamProfile> teamProfiles, String eventName) {
		HacakthonTeamResponse response = new HacakthonTeamResponse();
		response.setMessage("Successful");
		response.setSuccess(true);
		List<TeamProfileResponse> teams = response.getTeams();
		response.setEventName(eventName);
		Map<String, List<HackathonTeamProfile>> teamNametoProfileMap = teamProfiles.stream().collect(Collectors.groupingBy(t -> t.getTeamName() ));
		for (Entry<String, List<HackathonTeamProfile>> entry : teamNametoProfileMap.entrySet()) {
			TeamProfileResponse team = new TeamProfileResponse();
			team.setTeamName(entry.getKey());
			for(HackathonTeamProfile teamProfile: entry.getValue()) {
				if(teamProfile.isLead()){
					team.setSubmission(teamProfile.getSubmission());
					team.setScore(teamProfile.getScore());
				}
			}
			teams.add(team);
		}
		return response;
	}
}

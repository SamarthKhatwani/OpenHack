package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.OHConstants;
import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.resources.LeaderBoardResponse;
import sjsu.edu.cmpe275.api.resources.LeaderBoardTeam;
import sjsu.edu.cmpe275.api.resources.MemberDetail;

@Component
public class LeaderBoardResponseMapper {
	public LeaderBoardResponse mapLeaderBoardResponse(
			SortedMap<Float, Map<String, List<HackathonTeamProfile>>> teamsByScore) {
		LeaderBoardResponse leaderBoardResponse = new LeaderBoardResponse();
		leaderBoardResponse.setMessage("Successful");
		leaderBoardResponse.setSuccess(true);
		List<LeaderBoardTeam> result = leaderBoardResponse.getResult();
		int rank =0;
		for(Entry<Float, Map<String, List<HackathonTeamProfile>>> entry : teamsByScore.entrySet()) {
			rank++;
			for(Entry<String, List<HackathonTeamProfile>> teamEntry: entry.getValue().entrySet()) {
				LeaderBoardTeam leaderBoardTeam = new LeaderBoardTeam();
				result.add(leaderBoardTeam);
				leaderBoardTeam.setRank(rank);
				leaderBoardTeam.setTeamName(teamEntry.getKey());
				List<MemberDetail> teamMembers = leaderBoardTeam.getTeamMembers();
				boolean allPaid = true;
				for(HackathonTeamProfile teamProfile: teamEntry.getValue()) {
					MemberDetail teamMember = new MemberDetail();
					String[] split = teamProfile.getHackathonTeamProfile().split(OHConstants.DELIMIT_HACK_TEAM_PROFILE);
					teamMember.setEmail(split[2]);
					teamMember.setPaid(teamProfile.isPaid());
					if(!teamProfile.isPaid()) {
						allPaid = false;
					}
					teamMembers.add(teamMember);
					leaderBoardTeam.setScore(teamProfile.getScore());
				}
				leaderBoardTeam.setAllPaid(allPaid);
			}
		}
		return leaderBoardResponse;
	}
}

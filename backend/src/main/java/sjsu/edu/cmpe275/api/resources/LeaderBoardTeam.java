package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardTeam {
	private String teamName;
	
	private List<MemberDetail> teamMembers = new ArrayList<>();
    
	private int rank;
	
	private boolean allPaid;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<MemberDetail> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<MemberDetail> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isAllPaid() {
		return allPaid;
	}

	public void setAllPaid(boolean allPaid) {
		this.allPaid = allPaid;
	}
}

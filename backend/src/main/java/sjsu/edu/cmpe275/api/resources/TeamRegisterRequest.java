package sjsu.edu.cmpe275.api.resources;

import java.util.List;

import javax.validation.constraints.NotNull;

public class TeamRegisterRequest {
	
	@NotNull
	private String email;
	
	@NotNull
	private String teamName;
	
	@NotNull
	private List<TeamMemberRequest> teamMembers;
	
	@NotNull
	private String eventName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<TeamMemberRequest> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMemberRequest> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

}

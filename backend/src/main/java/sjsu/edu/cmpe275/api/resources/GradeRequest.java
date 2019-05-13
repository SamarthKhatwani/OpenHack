package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class GradeRequest {

	@NotNull
	private String eventName;
	
	@NotNull
	private String judge;

	private List<TeamProfileResponse> teams = new ArrayList<>();

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public List<TeamProfileResponse> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamProfileResponse> teams) {
		this.teams = teams;
	}
}

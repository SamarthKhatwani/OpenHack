package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class HacakthonTeamResponse {
	private boolean success;

	private String message;
	
	private String eventName;
	
	private List<TeamProfileResponse> teams = new ArrayList<>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<TeamProfileResponse> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamProfileResponse> teams) {
		this.teams = teams;
	}
}

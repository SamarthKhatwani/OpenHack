package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardResponse {
	private boolean success;
	
	private String message;
	
	private List<LeaderBoardTeam> result = new ArrayList<>();

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

	public List<LeaderBoardTeam> getResult() {
		return result;
	}

	public void setResult(List<LeaderBoardTeam> result) {
		this.result = result;
	}
}

package sjsu.edu.cmpe275.api.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AllHackathonResponse {
	
	private boolean success;
	
	private String message;
	
	@JsonIgnoreProperties(value = {"success", "message"})
	private List<HackathonResponse> results;

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

	public List<HackathonResponse> getResults() {
		return results;
	}

	public void setResults(List<HackathonResponse> results) {
		this.results = results;
	}
	
}

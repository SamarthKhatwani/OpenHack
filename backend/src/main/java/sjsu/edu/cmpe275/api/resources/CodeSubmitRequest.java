package sjsu.edu.cmpe275.api.resources;

public class CodeSubmitRequest {
	
	private String teamName;
	private String eventName;
	private String url;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public CodeSubmitRequest() {
		
	}
	

}

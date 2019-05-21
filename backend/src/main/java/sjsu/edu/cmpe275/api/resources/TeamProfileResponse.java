package sjsu.edu.cmpe275.api.resources;

public class TeamProfileResponse {
	
	private String teamName;
	
	private String submission;
	
	private Float score;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSubmission() {
		return submission;
	}

	public void setSubmission(String submission) {
		this.submission = submission;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	} 
}

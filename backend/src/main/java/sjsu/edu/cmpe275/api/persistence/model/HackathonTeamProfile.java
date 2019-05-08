package sjsu.edu.cmpe275.api.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HackathonTeamProfile {
	//String format should be hackathonEventName_#_TeamName_#_email
	@Id
	private String hackathonTeamProfile;
	
	private String role;
	
	private float amountPaid;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
	private Hackathon hackathon;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hacker")
	private Profile profile;
	
	private String teamName;
	
	private boolean isPaid;
	
	private boolean isLead;
	
	private String submission;
	
	private Float score;

	public String getHackathonTeamProfile() {
		return hackathonTeamProfile;
	}

	public void setHackathonTeamProfile(String hackathonTeamProfile) {
		this.hackathonTeamProfile = hackathonTeamProfile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Hackathon getHackathon() {
		return hackathon;
	}

	public void setHackathon(Hackathon hackathon) {
		this.hackathon = hackathon;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isLead() {
		return isLead;
	}

	public void setLead(boolean isLead) {
		this.isLead = isLead;
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

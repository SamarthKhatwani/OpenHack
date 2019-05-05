package sjsu.edu.cmpe275.api.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
	private Hackathon event;

	private String teamName;

	private String Submission;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	private List<TeamMember> teamMembers = new ArrayList<>();

	private boolean registrationSuccess;

	private Float score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Hackathon getEvent() {
		return event;
	}

	public void setEvent(Hackathon event) {
		this.event = event;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSubmission() {
		return Submission;
	}

	public void setSubmission(String submission) {
		Submission = submission;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

	public void setRegistrationSuccess(boolean registrationSuccess) {
		this.registrationSuccess = registrationSuccess;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

}

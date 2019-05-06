package sjsu.edu.cmpe275.api.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeamMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
	private Profile profile;

	private boolean isFeePaid;

	private String role;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
	private Team team;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isFeePaid() {
		return isFeePaid;
	}

	public void setFeePaid(boolean isFeePaid) {
		this.isFeePaid = isFeePaid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

package sjsu.edu.cmpe275.api.resources;

import javax.validation.constraints.NotNull;

public class TeamMemberRequest {

	private String role;

	@NotNull
	private String email;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

package sjsu.edu.cmpe275.api.resources;

import java.util.List;

public class OrganizationMembershipResponse {
	private boolean success;
	
	private String message;
	
	private List<OrganizationMemberships> organization;

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

	public List<OrganizationMemberships> getOrganization() {
		return organization;
	}

	public void setOrganization(List<OrganizationMemberships> organization) {
		this.organization = organization;
	}
}

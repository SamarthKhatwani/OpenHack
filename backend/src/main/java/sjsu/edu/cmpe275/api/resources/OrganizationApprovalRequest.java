package sjsu.edu.cmpe275.api.resources;

public class OrganizationApprovalRequest {
	
	private String email;
	
	private Boolean isApproved;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
}

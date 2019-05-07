package sjsu.edu.cmpe275.api.resources;

import java.util.List;

public class OrganizationMemberships {
	
	private String name;
	private List<OrganizationMembershipRequest> membershipRequet;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrganizationMembershipRequest> getMembershipRequet() {
		return membershipRequet;
	}
	public void setMembershipRequet(List<OrganizationMembershipRequest> membershipRequet) {
		this.membershipRequet = membershipRequet;
	}
	public void addMembershipRequest(OrganizationMembershipRequest organizationMembershipRequest) {
		this.membershipRequet.add(organizationMembershipRequest);
	}
}

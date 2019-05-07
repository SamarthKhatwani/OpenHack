package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class OrganizationMemberships {
	
	private String name;
	private List<OrganizationMembershipRequest> membershipRequest = new ArrayList<>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrganizationMembershipRequest> getMembershipRequest() {
		return membershipRequest;
	}
	public void setMembershipRequest(List<OrganizationMembershipRequest> membershipRequet) {
		this.membershipRequest = membershipRequet;
	}
	public void addMembershipRequest(OrganizationMembershipRequest organizationMembershipRequest) {
		this.membershipRequest.add(organizationMembershipRequest);
	}
}

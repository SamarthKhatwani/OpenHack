package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import sjsu.edu.cmpe275.api.persistence.model.Profile;

public class OrganizationResponse {
	
	private boolean success;
	
	private String message;

	private long id;

	private String name;
	
	private String address;
	
	private String description;

	private Profile owner;

	private List<Profile> members = new ArrayList<Profile>();
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public List<Profile> getRequests() {
		return this.getMembers().stream().filter(profile -> profile.isOrganizationApprovalStatus())
				.collect(Collectors.toList());
	}

	public List<Profile> getMembers() {
		return this.members;
	}

	public void setMembers(List<Profile> members) {
		this.members = members;
	}
}

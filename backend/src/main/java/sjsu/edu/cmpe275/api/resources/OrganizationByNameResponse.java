package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import sjsu.edu.cmpe275.api.persistence.model.Profile;

public class OrganizationByNameResponse {
	
	private boolean success;
	
	private String message;

	private List<String> organizationNames = new ArrayList<>();
	
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

	public List<String> getOrganizationNames() {
		return organizationNames;
	}

	public void setOrganizationNames(List<String> organizationNames) {
		this.organizationNames = organizationNames;
	}

}

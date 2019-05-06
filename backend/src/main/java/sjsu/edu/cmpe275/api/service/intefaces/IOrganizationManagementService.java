package sjsu.edu.cmpe275.api.service.intefaces;

import java.util.List;

import sjsu.edu.cmpe275.api.persistence.model.Organization;

public interface IOrganizationManagementService {
	public Organization getOrganization(String name);
	
	//public Organization createOrganization(String name, String ownerEmail, String description, String address);

	public List<Organization> getOrganizationByName(String name);
	
	public List<Organization> getOrganizationByNameIn(List<String> names);
	
}

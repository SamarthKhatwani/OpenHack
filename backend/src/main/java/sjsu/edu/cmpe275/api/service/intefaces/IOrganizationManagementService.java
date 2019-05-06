package sjsu.edu.cmpe275.api.service.intefaces;

import java.util.List;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.resources.OrganizationRequest;

public interface IOrganizationManagementService {
	public Organization getOrganization(String name);
	
	public Organization createOrganization(OrganizationRequest organizationRequests);

	public List<Organization> getOrganizationByName(String name);
	
	public List<Organization> getOrganizationByNameIn(List<String> names);
	
}

package sjsu.edu.cmpe275.api.service.intefaces;

import java.util.List;

import org.springframework.context.annotation.Profile;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.resources.OrganizationRequest;

public interface IOrganizationManagementService {
	public Organization getOrganization(String name);
	
	public Organization createOrganization(OrganizationRequest organizationRequests);

	public List<Organization> getOrganizationByName(String name);
	
	public List<Organization> getOrganizationByNameIn(List<String> names);
	
	public List<Profile> getRequestsByOrganizationName(String organization);
	
	public List<Organization> getOwnedOrganizations(String email);
}

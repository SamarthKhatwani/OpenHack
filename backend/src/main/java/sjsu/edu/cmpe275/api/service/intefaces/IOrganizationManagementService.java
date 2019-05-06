package sjsu.edu.cmpe275.api.service.intefaces;

import sjsu.edu.cmpe275.api.persistence.model.Organization;

public interface IOrganizationManagementService {
	public Organization getOrganizationByName(String name);
}

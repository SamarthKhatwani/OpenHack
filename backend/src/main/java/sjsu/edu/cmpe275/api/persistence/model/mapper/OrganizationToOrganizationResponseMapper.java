package sjsu.edu.cmpe275.api.persistence.model.mapper;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.resources.OrganizationResponse;

@Component
public class OrganizationToOrganizationResponseMapper {

	public OrganizationResponse map(Organization organization) {
		
		OrganizationResponse response = new OrganizationResponse();
		response.setAddress(organization.getAddress());
		response.setDescription(organization.getDescription());
		response.setName(organization.getName());
		response.setEmail(organization.getOwner()!=null?organization.getOwner().getEmail():null);
		response.setSuccess(true);
		response.setMessage("Success");
		return response;
		
	}
}

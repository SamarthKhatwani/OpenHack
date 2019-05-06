package sjsu.edu.cmpe275.api.persistence.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.resources.OrganizationResponse;
import sjsu.edu.cmpe275.api.resources.ProfileResponse;

@Component
public class OrganizationToOrganizationResponseMapper {
	
	@Autowired
	private ObjectMapper mapper;

	public OrganizationResponse map(Organization organization) {
		
		OrganizationResponse response = mapper.convertValue(organization, OrganizationResponse.class);
		response.setEmail(organization.getOwner()!=null?organization.getOwner().getEmail():null);
		response.setSuccess(true);
		response.setMessage("Success");
		return response;
		
	}
}

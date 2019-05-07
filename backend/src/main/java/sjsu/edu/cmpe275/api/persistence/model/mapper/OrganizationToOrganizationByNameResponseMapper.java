package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.resources.OrganizationByNameResponse;

@Component
public class OrganizationToOrganizationByNameResponseMapper {

	public OrganizationByNameResponse map(List<Organization> organizations) {
		OrganizationByNameResponse response = new OrganizationByNameResponse();
		response.setOrganizationNames(organizations.stream().map(org -> org.getName()).collect(Collectors.toList()));
		response.setSuccess(true);
		response.setMessage("Successful");
		return response;
	}
}

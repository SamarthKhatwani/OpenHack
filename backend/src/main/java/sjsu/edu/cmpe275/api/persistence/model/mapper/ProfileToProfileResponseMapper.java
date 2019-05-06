package sjsu.edu.cmpe275.api.persistence.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ProfileResponse;

@Component
public class ProfileToProfileResponseMapper {
	@Autowired
	private ObjectMapper mapper;

	public ProfileResponse map(Profile profile) {
		ProfileResponse response = mapper.convertValue(profile, ProfileResponse.class);
		response.setOrganization(profile.getOrganization()!=null?profile.getOrganization().getName():null);
		response.setSuccess(true);
		response.setMessage("Successful");
		response.setAdmin(profile.isAmdin());
		return response;
	}
}

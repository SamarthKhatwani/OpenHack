package sjsu.edu.cmpe275.api.persistence.model.mapper;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ProfileResponse;

@Component
public class ProfileToProfileResponseMapper {

	public ProfileResponse map(Profile profile) {
		ProfileResponse response = new ProfileResponse();
		response.setAboutMe(profile.getAboutMe());
		response.setAddress(profile.getAddress());
		response.setAdmin(profile.isAmdin());
		response.setBusinessTitle(profile.getBusinessTitle());
		response.setEmail(profile.getEmail());
		response.setName(profile.getName());
		response.setOrganizationApprovalStatus(profile.getOrganizationApprovalStatus());
		response.setPortraitUrl(profile.getPortraitUrl());
		response.setScreenName(profile.getScreenName());
		response.setOrganization(profile.getOrganization()!=null?profile.getOrganization().getName():null);
		response.setSuccess(true);
		response.setMessage("Successful");
		
		return response;
	}
}

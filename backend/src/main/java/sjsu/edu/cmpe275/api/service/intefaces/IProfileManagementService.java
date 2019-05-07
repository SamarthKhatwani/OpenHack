package sjsu.edu.cmpe275.api.service.intefaces;

import java.util.List;

import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;

public interface IProfileManagementService {

	public Profile getProfile(String email);

	public Profile getProfileByScreenName(String screenName);

	public Profile updateProfile(ProfileRequest profileRequest);

	public Profile createProfile(ProfileRequest profileRequest);

	public List<Profile> getProfileByEmailIn(List<String> emails);

	public Profile updateOrganizationApprovalStatus(Boolean isApproved, String email);
}

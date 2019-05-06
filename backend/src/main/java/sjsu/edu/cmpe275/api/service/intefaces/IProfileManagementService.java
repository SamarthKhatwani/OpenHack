package sjsu.edu.cmpe275.api.service.intefaces;

import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;

public interface IProfileManagementService {
	
	public Profile getProfile(String email);
	
	public Profile getProfileByScreenName(String screenName);
	
	public Profile updateProfile(ProfileRequest profileRequest);
	
	public Profile createProfile(ProfileRequest profileRequest);
	
}

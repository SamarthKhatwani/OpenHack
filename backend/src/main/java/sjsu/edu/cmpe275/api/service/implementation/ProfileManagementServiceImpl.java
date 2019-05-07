package sjsu.edu.cmpe275.api.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.repository.ProfileRepository;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Service
public class ProfileManagementServiceImpl implements IProfileManagementService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private IOrganizationManagementService organizationManagementService;
	
	@Override
	@Transactional(readOnly=true)
	public Profile getProfile(String email) {
		Optional<Profile> profileWrapper = profileRepository.findByEmail(email);
		if (profileWrapper.isPresent()) {
			Profile profile = profileWrapper.get();
			Utils.fetchLazyAttributeFromProfile(profile);
			return profile;

		} else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public Profile updateProfile(ProfileRequest profileRequest) {
		Optional<Profile> profileWrapper = profileRepository.findByEmail(profileRequest.getEmail());
		if (profileWrapper.isPresent()) {
			Profile profile = profileWrapper.get();
			profile.setAboutMe(profileRequest.getAboutMe());
			profile.setAddress(profileRequest.getAddress());
			profile.setBusinessTitle(profileRequest.getBusinessTitle());
			profile.setName(profileRequest.getName());
			profile.setPortraitUrl(profileRequest.getPortraitUrl());
			if(profileRequest.getOrganization()!=null) {
				if(profile.getOrganization()==null || !profileRequest.getOrganization().equals(profile.getOrganization().getName())) {
					profile.setOrganizationApprovalStatus(false);
					Organization organization = organizationManagementService.getOrganization(profileRequest.getOrganization());
					profile.setOrganization(organization);
				}
			}else {
				profile.setOrganizationApprovalStatus(false);
				profile.setOrganization(null);
			}
			profileRepository.save(profile);
			return profile;
		} else {
			return null;
		}
	}

	
	@Override
	@Transactional(readOnly=true)
	public Profile getProfileByScreenName(String screenName) {
		Optional<Profile> profileWrapper = profileRepository.findByScreenName(screenName);
		if (profileWrapper.isPresent()) {
			Profile profile = profileWrapper.get();
			Utils.fetchLazyAttributeFromProfile(profile);
			return profile;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Profile createProfile(ProfileRequest profileRequest) {
		Profile profile = new Profile();
		profile.setAboutMe(profileRequest.getAboutMe());
		profile.setAddress(profileRequest.getAddress());
		profile.setBusinessTitle(profileRequest.getBusinessTitle());
		profile.setEmail(profileRequest.getEmail());
		if(profileRequest.getEmail().endsWith("@sjsu.edu")) {
			profile.setAmdin(true);	
		}
		profile.setName(profileRequest.getName());
		if(profileRequest.getOrganization()!=null) {
			Organization organization = organizationManagementService.getOrganization(profileRequest.getOrganization());
			profile.setOrganization(organization);
		}
		profile.setOrganizationApprovalStatus(false);
		profile.setPortraitUrl(profileRequest.getPortraitUrl());
		profile.setScreenName(profileRequest.getScreenName());
		return profileRepository.save(profile);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Profile> getProfileByEmailIn(List<String> emails) {
		return profileRepository.findByEmailIn(emails);
	}

	@Override
	public Profile updateOrganizationApprovalStatus(Boolean isApproved, String email) {
		Optional<Profile> profileWrapper = profileRepository.findByEmail(email);
		if (profileWrapper.isPresent()) {
			Profile profile = profileWrapper.get();
			if(isApproved == true) {
				profile.setOrganizationApprovalStatus(true);
			}else {
				profile.setOrganizationApprovalStatus(false);
				profile.setOrganization(null);
			}
			profileRepository.save(profile);
			return profile;
		} else {
			return null;
		}
	}

}

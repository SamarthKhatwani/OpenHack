package sjsu.edu.cmpe275.api.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.repository.OrganizationRepository;
import sjsu.edu.cmpe275.api.persistence.repository.ProfileRepository;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Service
public class ProfileManagementServiceImpl implements IProfileManagementService {

	private ProfileRepository profileRepository;
	private OrganizationRepository organizationRepository;
	
	@Autowired
	public ProfileManagementServiceImpl(ProfileRepository profileRepository,
			OrganizationRepository organizationRepository){
		this.profileRepository = profileRepository;
		this.organizationRepository = organizationRepository;
	}
	
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
				Optional<Organization> organizationWrapper = organizationRepository.findByName(profileRequest.getOrganization());
				if (!organizationWrapper.isPresent()) {
					throw new  BadRequestException("Organization Doesn't Exist");
				}
				if(profile.getOrganization()==null || !profileRequest.getOrganization().equals(profile.getOrganization().getName())) {
					profile.setOrganizationApprovalStatus(false);
					profile.setOrganization(organizationWrapper.get());
				}
			}else {
				profile.setOrganizationApprovalStatus(false);
			}
			profileRepository.save(profile);
			return profile;
		} else {
			return null;
		}
	}
	

}

package sjsu.edu.cmpe275.api.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.repository.OrganizationRepository;
import sjsu.edu.cmpe275.api.persistence.repository.ProfileRepository;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;

@Service
public class OrganizationManagementServiceImpl implements IOrganizationManagementService{
	
	private ProfileRepository profileRepository;
	private OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationManagementServiceImpl(ProfileRepository profileRepository,
			OrganizationRepository organizationRepository){
		this.profileRepository = profileRepository;
		this.organizationRepository = organizationRepository;
	}

	@Override
	@Transactional(readOnly=true)
	public Organization getOrganization(String name) {
		Optional<Organization> organizationWrapper = organizationRepository.findByName(name);
		if(organizationWrapper.isPresent()) {
			Organization organization = organizationWrapper.get();
			Utils.fetchLazyAttributeFromOrganization(organization);
			return organization;
		}else {
			return null;
		}
	}

}

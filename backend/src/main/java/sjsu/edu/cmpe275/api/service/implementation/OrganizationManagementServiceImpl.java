package sjsu.edu.cmpe275.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.repository.OrganizationRepository;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Service
public class OrganizationManagementServiceImpl implements IOrganizationManagementService{
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private IProfileManagementService profileManagementService;

	@Override
	@Transactional(readOnly=true)
	public Organization getOrganization(String name) {
		if(name==null) {
			return null;
		}
		Optional<Organization> organizationWrapper = organizationRepository.findByName(name);
		if (organizationWrapper.isPresent()) {
			return organizationWrapper.get();
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly =true)
	public List<Organization> getOrganizationByName(String name){
		if(name == null) {
			return new ArrayList<Organization>();
		}
		List<Organization> organizationList = organizationRepository.findByNameContaining(name);
		return organizationList;
	}

//	@Override
//	public Organization createOrganization(String name, String ownerEmail, String description, String address) {
//		
//		Organization organization = new Organization();
//		Optional<Organization> organizationWrapper = organizationRepository.findByName(name);
//		if(organizationWrapper.isPresent()) {
//			return null;
//		}
//		organization.setName(name);
//		Profile profile = profileManagementService.getProfile(ownerEmail);
//		organization.setDescription(description);
//		organization.setAddress(address);
//		return organizationRepository.save(organization);
//	}

}

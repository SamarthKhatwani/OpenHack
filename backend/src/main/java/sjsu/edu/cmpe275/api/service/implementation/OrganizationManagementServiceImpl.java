package sjsu.edu.cmpe275.api.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.repository.OrganizationRepository;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;

@Service
public class OrganizationManagementServiceImpl implements IOrganizationManagementService{
	
	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	@Transactional(readOnly=true)
	public Organization getOrganizationByName(String name) {
		if(name==null) {
			return null;
		}
		Optional<Organization> organizationWrapper = organizationRepository.findByName(name);
		if (organizationWrapper.isPresent()) {
			return organizationWrapper.get();
		}
		return null;
	}

}

package sjsu.edu.cmpe275.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.repository.OrganizationRepository;
import sjsu.edu.cmpe275.api.resources.OrganizationRequest;
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

	@Override
	public Organization createOrganization(OrganizationRequest organizationRequest) {
		if(organizationRequest.getEmail() == null) {
			throw new BadRequestException("Owner mail not provided");
		}
		if(profileManagementService.getProfile(organizationRequest.getEmail())==null) {
			throw new BadRequestException("User with given email doesn't exist");
		}
		Organization alternateOrganization = getOrganization(organizationRequest.getName());
		if(alternateOrganization != null) {
			throw new BadRequestException("Organization with the same name already exists");
		}
		Organization organization = new Organization();
		organization.setDescription(organizationRequest.getDescription());
		organization.setAddress(organizationRequest.getAddress());
		organization.setName(organizationRequest.getName());
		Profile owner = profileManagementService.getProfile(organizationRequest.getEmail());
		organization.setOwner(owner);
		return organizationRepository.save(organization);
	}

	public List<Organization> getOrganizationByNameIn(List<String> names) {
		return organizationRepository.findByNameIn(names);
	}

	@Override
	public List<org.springframework.context.annotation.Profile> getRequestsByOrganizationName(String organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organization> getOwnedOrganizations(String email) {
		return organizationRepository.findByOwner(email);
	}

	

}

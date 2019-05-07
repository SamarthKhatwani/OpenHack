package sjsu.edu.cmpe275.api.controller.implementaion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.INormalAuthAPI;
import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.model.mapper.OrganizationToOrganizationByNameResponseMapper;
import sjsu.edu.cmpe275.api.persistence.model.mapper.OrganizationToOrganizationResponseMapper;
import sjsu.edu.cmpe275.api.persistence.model.mapper.ProfileToProfileResponseMapper;
import sjsu.edu.cmpe275.api.resources.OrganizationByNameResponse;
import sjsu.edu.cmpe275.api.resources.OrganizationMembershipRequest;
import sjsu.edu.cmpe275.api.resources.OrganizationMemberships;
import sjsu.edu.cmpe275.api.resources.OrganizationRequest;
import sjsu.edu.cmpe275.api.resources.OrganizationResponse;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;
import sjsu.edu.cmpe275.api.resources.ProfileResponse;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Controller
public class NormalAuthAPI implements INormalAuthAPI {

	@Autowired
	private IProfileManagementService profileManagementService;
	
	@Autowired
	private IOrganizationManagementService organizationManagementService;
	
	@Autowired
	private ProfileToProfileResponseMapper profileToProfileResponseMapper;
	
	@Autowired
	private OrganizationToOrganizationByNameResponseMapper organizationToOrganizationByNameResponseMapper;
	
	@Autowired
	private OrganizationToOrganizationResponseMapper organizationToOrganizationResponseMapper;
	
	@Override
	public ResponseEntity<Object> getProfile(String token, String email) {
		Profile profile = profileManagementService.getProfile(email);
		if(profile == null) {
			return new ResponseEntity<Object>(new ResponseMessage(false,"Profile with given email doesn't exist" ), HttpStatus.NOT_FOUND);
		}
		ProfileResponse response = profileToProfileResponseMapper.map(profile);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateProfile(String token, ProfileRequest profileRequest) {
		Profile profile = profileManagementService.updateProfile(profileRequest);
		if(profile != null ) {
			ProfileResponse response = profileToProfileResponseMapper.map(profile);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Object>(new ResponseMessage(false,"Profile with given email doesn't exist" ), HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<Object> searchOrganization(String token, String name) {
		List<Organization> organizations = organizationManagementService.getOrganizationByName(name);
		OrganizationByNameResponse response = organizationToOrganizationByNameResponseMapper.map(organizations);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> createOrganization(String token, OrganizationRequest organizationRequest) {
		Organization organization = organizationManagementService.createOrganization(organizationRequest);
		OrganizationResponse response = organizationToOrganizationResponseMapper.map(organization);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Object> listRequestOrganizaion(String token, String email) {
		List<Organization> myOrganizations = organizationManagementService.getOwnedOrganizations(email);
		List<OrganizationMemberships> response = new ArrayList<OrganizationMemberships>();
		for (Organization myOrgaanization : myOrganizations) {
			OrganizationMemberships organizationMembership = new OrganizationMemberships();
			organizationMembership.setName(myOrgaanization.getName());
			List<Profile> membershipRequests = myOrgaanization.getRequests();
			for (Profile profile : membershipRequests) {
				OrganizationMembershipRequest organizationMembershipRequest = new OrganizationMembershipRequest();
				organizationMembershipRequest.setEmail(profile.getEmail());
				organizationMembershipRequest.setName(profile.getName());
				organizationMembership.addMembershipRequest(organizationMembershipRequest);
			}
			if (!membershipRequests.isEmpty()) {
				response.add(organizationMembership);
			}

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

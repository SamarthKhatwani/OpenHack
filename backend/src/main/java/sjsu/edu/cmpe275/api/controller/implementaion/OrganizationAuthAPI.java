package sjsu.edu.cmpe275.api.controller.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.IOrganizationAuthAPI;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.OrganizationApprovalRequest;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Controller
public class OrganizationAuthAPI implements IOrganizationAuthAPI {
	
	@Autowired
	private IProfileManagementService profileManagementService;

	@Override
	public ResponseEntity<Object> respondRequestOrganizaion(String token,
			OrganizationApprovalRequest organizationApprovalRequest) {
		Profile profile = profileManagementService.updateOrganizationApprovalStatus(organizationApprovalRequest.getIsApproved(), organizationApprovalRequest.getEmail());
		if(profile == null) {
			ResponseMessage response = new ResponseMessage(false, "User with the given email doesn't exist");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}else {
			ResponseMessage response = new ResponseMessage(true, "Successful");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	

	
	
}



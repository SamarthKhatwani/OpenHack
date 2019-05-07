package sjsu.edu.cmpe275.api.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sjsu.edu.cmpe275.api.resources.OrganizationApprovalRequest;

public interface IOrganizationAuthAPI {
	
	@RequestMapping(value = "/respondRequestOrganizaion", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> respondRequestOrganizaion(@RequestHeader(value = "Authorization") String token,
			@RequestBody OrganizationApprovalRequest organizationApprovalRequest);
}

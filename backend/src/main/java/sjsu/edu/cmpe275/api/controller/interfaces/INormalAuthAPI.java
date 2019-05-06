package sjsu.edu.cmpe275.api.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sjsu.edu.cmpe275.api.resources.ProfileRequest;

public interface INormalAuthAPI {

	@RequestMapping(value = "/getProfile", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<Object> getProfile(@RequestHeader(value = "Authorization") String token,
			@RequestParam(value = "email", required = true) String email);

	@RequestMapping(value = "/updateProfile", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> updateProfile(@RequestHeader(value = "Authorization") String token,
			@RequestBody ProfileRequest profileRequest);
	
	//Organizations
	
	@RequestMapping(value = "/getOrganization/", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	ResponseEntity<Object> getOrganization(@RequestHeader(value = "Authorization") String token,
			@RequestParam(value = "name", required = true) String name);
	
	@RequestMapping(value = "/createOrganization", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	ResponseEntity<Object> createOrganization(@RequestHeader(value = "Authorization") String token,
			@RequestParam(value = "email", required = true) String email);


}

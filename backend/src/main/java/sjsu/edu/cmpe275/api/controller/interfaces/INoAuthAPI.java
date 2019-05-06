package sjsu.edu.cmpe275.api.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sjsu.edu.cmpe275.api.resources.ProfileRequest;

public interface INoAuthAPI {
	@RequestMapping(value = "/lookupScreenName", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	ResponseEntity<Object> lookupSN(@RequestParam(value = "screenName", required = true) String screenName);

	@RequestMapping(value = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> signup(@RequestBody ProfileRequest profileRequest);
}

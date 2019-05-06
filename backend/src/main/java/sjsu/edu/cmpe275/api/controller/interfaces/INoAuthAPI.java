package sjsu.edu.cmpe275.api.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface INoAuthAPI {
	@RequestMapping(value = "/lookupSN", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	ResponseEntity<Object> lookupSN(
			@RequestParam(value = "screenName", required = true) String screenName);
	

}

package sjsu.edu.cmpe275.api.controller.interfaces;

import java.text.ParseException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;

public interface IHackerAuthAPI {
	
	@RequestMapping(value = "/registerHackathon", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> registerHackathon(@RequestHeader(value = "Authorization") String token,
			@RequestBody TeamRegisterRequest teamRegisterRequest) throws ParseException;
}

package sjsu.edu.cmpe275.api.controller.interfaces;

import java.text.ParseException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sjsu.edu.cmpe275.api.resources.HackathonRequest;

public interface IAdminAuthAPI {
	
	@RequestMapping(value = "/createUpdateHackathon", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> createUpdateHackathon(@RequestHeader(value = "Authorization") String token,
			@RequestBody HackathonRequest hackathonRequest) throws ParseException;
	
	@RequestMapping(value = "/financialReport", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<Object> financialReport(@RequestHeader(value = "Authorization") String token,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "eventName", required = true) String eventName) throws ParseException;
}

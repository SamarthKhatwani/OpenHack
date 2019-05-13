package sjsu.edu.cmpe275.api.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sjsu.edu.cmpe275.api.resources.GradeRequest;

public interface IJudgeAuthAPI {
	
	@RequestMapping(value = "/grade", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Object> grade(@RequestHeader(value = "Authorization") String token,
			@RequestBody GradeRequest gradeRequest);
}

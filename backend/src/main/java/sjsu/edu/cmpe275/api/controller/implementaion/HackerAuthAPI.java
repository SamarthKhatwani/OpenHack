package sjsu.edu.cmpe275.api.controller.implementaion;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;

@Controller
public class HackerAuthAPI implements IHackerAuthAPI {

	@Autowired
	private IHackathonManagementService hackathonManagementService;

	@Override
	public ResponseEntity<Object> registerHackathon(String token, TeamRegisterRequest teamRegisterRequest) throws ParseException {
		hackathonManagementService.registerUserHackathon(teamRegisterRequest);
		ResponseMessage response = new ResponseMessage(true, "Successful");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
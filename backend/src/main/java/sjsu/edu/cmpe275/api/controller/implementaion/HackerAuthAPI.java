package sjsu.edu.cmpe275.api.controller.implementaion;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI;
import sjsu.edu.cmpe275.api.resources.CodeSubmitRequest;
import sjsu.edu.cmpe275.api.resources.Quotation;
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

	@Override
	public ResponseEntity<Object> fetchPriceForPayment(String token, String email, String eventName)throws ParseException {
		Quotation quotation=hackathonManagementService.fetchQuotation(email, eventName);
		return new ResponseEntity<Object>(quotation, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> makePayment(String token, Quotation quotation) throws ParseException {
		hackathonManagementService.makePayment(quotation);
		ResponseMessage response = new ResponseMessage(true, "Successful");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<Object> submitCode(String token, CodeSubmitRequest codeSubmitRequest) throws ParseException {
		hackathonManagementService.submitCode(codeSubmitRequest.getTeamName(), codeSubmitRequest.getEventName(), codeSubmitRequest.getUrl());
		ResponseMessage response = new ResponseMessage(true, "Successful");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	

}

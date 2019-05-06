package sjsu.edu.cmpe275.api.controller.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI;
import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.mapper.HackathonToHackathonReponseMapper;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;

@Controller
public class AdminAuthAPI implements IAdminAuthAPI {

	@Autowired
	private IHackathonManagementService hackathonManagementService;
	
	@Autowired
	private HackathonToHackathonReponseMapper hackathonToHackathonReponseMapper;
	
	@Override
	public ResponseEntity<Object> createUpdateHackathon(String token, HackathonRequest hackathonRequest) {
		Hackathon hackathon = hackathonManagementService.createOrUpdateHackathon(hackathonRequest);
		HackathonResponse response = hackathonToHackathonReponseMapper.map(hackathon);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}

package sjsu.edu.cmpe275.api.controller.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.IJudgeAuthAPI;
import sjsu.edu.cmpe275.api.resources.GradeRequest;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;

@Controller
public class JudgeAuthAPI implements IJudgeAuthAPI {

	@Autowired
	private IHackathonManagementService hackathonManagementService;
	
	@Override
	public ResponseEntity<Object> grade(String token, GradeRequest gradeRequest) {
		hackathonManagementService.gradeHackathon(gradeRequest);
		return new ResponseEntity<Object>(new ResponseMessage(true, "Successful"), HttpStatus.OK);
	}

}

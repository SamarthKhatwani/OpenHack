package sjsu.edu.cmpe275.api.controller.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sjsu.edu.cmpe275.api.controller.interfaces.INoAuthAPI;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Controller
public class NoAuthAPI implements INoAuthAPI{
	
	@Autowired
	private IProfileManagementService profileManagementService;
	
	@Override
	public ResponseEntity<Object> lookupSN(String screenName) {
		Profile profile = profileManagementService.getProfileByScreenName(screenName);
		if(profile == null) {
			return new ResponseEntity<Object>(new ResponseMessage(false,"Profile with given screenName doesn't exist" ), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new ResponseMessage(true, "Profile with given screenName exist"), HttpStatus.OK);
	}

}

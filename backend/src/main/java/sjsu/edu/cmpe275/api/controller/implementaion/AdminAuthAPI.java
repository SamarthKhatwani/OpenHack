package sjsu.edu.cmpe275.api.controller.implementaion;

import org.springframework.http.ResponseEntity;

import sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;

public class AdminAuthAPI implements IAdminAuthAPI {

	@Override
	public ResponseEntity<Object> createUpdateHackathon(String token, HackathonRequest profileRequest) {
		return null;
	}

}

package sjsu.edu.cmpe275.api.service.intefaces;

import java.text.ParseException;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;

public interface IHackathonManagementService {

	public Hackathon createOrUpdateHackathon(HackathonRequest hackathonRequest) throws ParseException;
	
	public boolean registerUserHackathon(TeamRegisterRequest teamRegisterRequest) throws ParseException;
}

package sjsu.edu.cmpe275.api.service.intefaces;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;

public interface IHackathonManagementService {

	public Hackathon createOrUpdateHackathon(HackathonRequest hackathonRequest);
}

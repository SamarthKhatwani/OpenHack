package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.text.ParseException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;

@Component
public class HackathonToHackathonReponseMapper {

	public HackathonResponse map(Hackathon hackathon, boolean mapJudge, boolean mapSponsor, boolean mapTeam) throws ParseException {
		HackathonResponse response = new HackathonResponse();
		response.setCloseDate(hackathon.getCloseDate());
		response.setDescription(hackathon.getDescription());
		response.setDiscount(hackathon.getDiscount());
		response.setEndDate(hackathon.getEndDate());
		response.setEventName(hackathon.getEventName());
		response.setFinalized(hackathon.isFinalized());
		response.setOpenDate(hackathon.getOpenDate());
		response.setRegistrationFee(hackathon.getRegistrationFee());
		response.setStartDate(hackathon.getStartDate());
		response.setTeamMaxSize(hackathon.getTeamMaxSize());
		response.setTeamMinSize(hackathon.getTeamMinSize());
		if(mapJudge){
			response.setJudges(hackathon.getJudges().stream().map(h->h.getEmail()).collect(Collectors.toList()));
		}
		if(mapSponsor){
			response.setSponsors(hackathon.getSponsors().stream().map(h->h.getName()).collect(Collectors.toList()));
		}
		response.setSuccess(true);
		response.setMessage("Successful");
		return response;
	}
}

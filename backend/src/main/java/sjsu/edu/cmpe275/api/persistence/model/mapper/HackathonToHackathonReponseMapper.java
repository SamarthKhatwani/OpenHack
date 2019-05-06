package sjsu.edu.cmpe275.api.persistence.model.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.resources.HackathonResponse;

@Component
public class HackathonToHackathonReponseMapper {
	@Autowired
	private ObjectMapper mapper;

	public HackathonResponse map(Hackathon hackathon) {
		HackathonResponse response = mapper.convertValue(hackathon, HackathonResponse.class);
		response.setJudges(hackathon.getJudges().stream().map(h->h.getEmail()).collect(Collectors.toList()));
		response.setSponsors(hackathon.getSponsors().stream().map(h->h.getName()).collect(Collectors.toList()));
		response.setSuccess(true);
		response.setMessage("Successful");
		return response;
	}
}

package sjsu.edu.cmpe275.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.model.Team;
import sjsu.edu.cmpe275.api.persistence.repository.HackathonRepository;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Service
public class HackathonManagementService implements IHackathonManagementService {

	@Autowired
	private HackathonRepository hackathonRepository;

	@Autowired
	private IProfileManagementService profileManagementService;

	@Autowired
	private IOrganizationManagementService organizationManagementService;

	@Override
	@Transactional
	public Hackathon createOrUpdateHackathon(HackathonRequest hackathonRequest) {
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(hackathonRequest.getEventName());
		Hackathon hackathon = null;
		if (hackathonWrapper.isPresent()) {
			hackathon = hackathonWrapper.get();
		} else {
			hackathon = new Hackathon();
			hackathon.setEventName(hackathonRequest.getEventName());
		}
		mapBasicHackathonRequestParam(hackathonRequest, hackathon);
		mapIsFinalized(hackathonRequest, hackathon);
		mapJudges(hackathonRequest, hackathon);
		mapSponsors(hackathonRequest, hackathon);
		return hackathonRepository.save(hackathon);
	}

	private void mapSponsors(HackathonRequest hackathonRequest, Hackathon hackathon) {
		if(hackathonRequest.getJudges()==null || hackathonRequest.getJudges().isEmpty()) {
			hackathon.setSponsors(new ArrayList<>());
			return;
		}
		List<Organization> sponsors = organizationManagementService
				.getOrganizationByNameIn(hackathonRequest.getSponsors());
		if (sponsors.size() != hackathonRequest.getSponsors().size()) {
			List<String> invalidSponsors = hackathonRequest.getSponsors().stream()
					.filter(s -> !sponsors.contains(s)).collect(Collectors.toList());
			throw new BadRequestException("Sponsors " + invalidSponsors + " doesn't exist");
		}
		hackathon.setSponsors(sponsors);
	}

	private void mapJudges(HackathonRequest hackathonRequest, Hackathon hackathon) {
		if(hackathonRequest.getJudges()==null || hackathonRequest.getJudges().isEmpty()) {
			throw new BadRequestException("Atleast one Judge is required");
		}
		List<Profile> judges = profileManagementService.getProfileByEmailIn(hackathonRequest.getJudges());
		if (judges.size() != hackathonRequest.getJudges().size()) {
			List<String> invalidEmails = hackathonRequest.getJudges().stream().filter(j -> !judges.contains(j))
					.collect(Collectors.toList());
			throw new BadRequestException("Judges " + invalidEmails + " doesn't exist");
		}
		hackathon.setJudges(judges);
	}

	private void mapIsFinalized(HackathonRequest hackathonRequest, Hackathon hackathon) {
		List<Team> teams = hackathon.getTeams();

		if (teams.isEmpty() || !hackathonRequest.isFinalized()) {
			hackathon.setFinalized(hackathonRequest.isFinalized());
		} else {
			boolean canFinalize = true;
			for (Team team : teams) {
				if (team.isRegistrationSuccess() && team.getScore() == null) {
					canFinalize = false;
					break;
				}
			}
			if (canFinalize == hackathonRequest.isFinalized()) {
				hackathon.setFinalized(hackathonRequest.isFinalized());
			} else {
				throw new BadRequestException("Can not finalize hackathon, Some registered team have not been graded");
			}
		}
	}

	private void mapBasicHackathonRequestParam(HackathonRequest hackathonRequest, Hackathon hackathon) {
		hackathon.setCloseDate(hackathonRequest.getCloseDate());
		hackathon.setDescription(hackathonRequest.getDescription());
		hackathon.setDiscount(hackathonRequest.getDiscount());
		hackathon.setEndDate(hackathonRequest.getEndDate());
		hackathon.setOpenDate(hackathonRequest.getOpenDate());
		hackathon.setRegistrationFee(hackathonRequest.getRegistrationFee());
		hackathon.setStartDate(hackathonRequest.getStartDate());
		hackathon.setTeamMaxSize(hackathonRequest.getTeamMaxSize());
		hackathon.setTeamMinSize(hackathonRequest.getTeamMinSize());
	}

}

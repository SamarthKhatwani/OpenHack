package sjsu.edu.cmpe275.api.service.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjsu.edu.cmpe275.OHConstants;
import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.persistence.model.Organization;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.persistence.repository.HacathonTeamProfileRepository;
import sjsu.edu.cmpe275.api.persistence.repository.HackathonRepository;
import sjsu.edu.cmpe275.api.resources.GradeRequest;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.resources.Quotation;
import sjsu.edu.cmpe275.api.resources.TeamMemberRequest;
import sjsu.edu.cmpe275.api.resources.TeamProfileResponse;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IHackathonManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Service
public class HackathonManagementService implements IHackathonManagementService {

	@Autowired
	private HackathonRepository hackathonRepository;

	@Autowired
	private HacathonTeamProfileRepository hacathonTeamProfileRepository;

	@Autowired
	private IProfileManagementService profileManagementService;

	@Autowired
	private IOrganizationManagementService organizationManagementService;

	@Override
	@Transactional
	public Hackathon createOrUpdateHackathon(HackathonRequest hackathonRequest) throws ParseException {
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

	@Override
	@Transactional
	public boolean registerUserHackathon(TeamRegisterRequest teamRegisterRequest) throws ParseException {
		if (teamRegisterRequest.getTeamMembers() == null || teamRegisterRequest.getTeamMembers().isEmpty()) {
			throw new BadRequestException("Atleast one team member is required");
		}
		if (!teamRegisterRequest.getTeamMembers().stream()
				.anyMatch(mem -> mem.getEmail().contains(teamRegisterRequest.getEmail()))) {
			throw new BadRequestException("You should be ateam member");
		}
		List<Profile> userToBeRegistered = new ArrayList<>();
		List<String> roles = new ArrayList<>();
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(teamRegisterRequest.getEventName());
		Hackathon hackathon = validateRegister(hackathonWrapper, teamRegisterRequest, userToBeRegistered, roles);

		int i = 0;
		for (Profile user : userToBeRegistered) {
			HackathonTeamProfile member = new HackathonTeamProfile();
			if (user.getEmail().equals(teamRegisterRequest.getEmail())) {
				member.setLead(true);
			}
			member.setHackathon(hackathon);
			member.setHackathonTeamProfile(teamRegisterRequest.getEventName() + OHConstants.DELIMIT_HACK_TEAM_PROFILE
					+ teamRegisterRequest.getTeamName() + OHConstants.DELIMIT_HACK_TEAM_PROFILE + user.getEmail());
			member.setProfile(user);
			member.setRole(roles.get(i++));
			member.setTeamName(teamRegisterRequest.getTeamName());
			hacathonTeamProfileRepository.save(member);
		}
		return true;
	}

	private Hackathon validateRegister(Optional<Hackathon> hackathonWrapper, TeamRegisterRequest teamRegisterRequest,
			List<Profile> userToBeRegistered, List<String> roles) throws ParseException {
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("Hackathon doesn't exist with given name");
		}
		Hackathon hackathon = hackathonWrapper.get();
		if (hackathon.getTeamMinSize() > teamRegisterRequest.getTeamMembers().size()) {
			throw new BadRequestException("minimum team member should be" + hackathon.getTeamMinSize());
		}
		if (hackathon.getTeamMaxSize() < teamRegisterRequest.getTeamMembers().size()) {
			throw new BadRequestException("maximum team member should be" + hackathon.getTeamMaxSize());
		}
		if (hackathon.isFinalized()) {
			throw new BadRequestException("Hackathon already finalized");
		}
		if (hackathon.isOpen()) {
			throw new BadRequestException("Hackathon already started can't register");
		}
		List<String> reservedNames = hackathon.getTeams().stream().map(team -> team.getTeamName())
				.collect(Collectors.toList());

		List<String> reservedEmails = hackathon.getTeams().stream().map(team -> {
			String[] splits = team.getHackathonTeamProfile().split(OHConstants.DELIMIT_HACK_TEAM_PROFILE);
			return splits[2];
		}).collect(Collectors.toList());

		List<String> judgeEmails = hackathon.getJudges().stream().map(judge -> judge.getEmail())
				.collect(Collectors.toList());

		if (reservedNames.contains(teamRegisterRequest.getTeamName())) {
			throw new BadRequestException("Team name already exist for this hackathon");
		}

		for (TeamMemberRequest member : teamRegisterRequest.getTeamMembers()) {
			Profile profile = profileManagementService.getProfile(member.getEmail());
			if (profile == null) {
				throw new BadRequestException("user doesn't exist with email " + member.getEmail());
			}
			if (reservedEmails.contains(member.getEmail())) {
				throw new BadRequestException("user with email already registered " + member.getEmail());
			}
			if (judgeEmails.contains(member.getEmail())) {
				throw new BadRequestException("user is already a judge " + member.getEmail());
			}
			if (profile.isAmdin()) {
				throw new BadRequestException("user is already a admin " + member.getEmail());
			}
			userToBeRegistered.add(profile);
			roles.add(member.getRole());
		}
		return hackathon;
	}

	private void mapSponsors(HackathonRequest hackathonRequest, Hackathon hackathon) {
		if (hackathonRequest.getJudges() == null || hackathonRequest.getJudges().isEmpty()) {
			hackathon.setSponsors(new ArrayList<>());
			return;
		}
		List<Organization> sponsors = organizationManagementService
				.getOrganizationByNameIn(hackathonRequest.getSponsors());
		if (sponsors.size() != hackathonRequest.getSponsors().size()) {
			List<String> invalidSponsors = hackathonRequest.getSponsors().stream().filter(s -> !sponsors.contains(s))
					.collect(Collectors.toList());
			throw new BadRequestException("Sponsors " + invalidSponsors + " doesn't exist");
		}
		hackathon.setSponsors(sponsors);
	}

	private void mapJudges(HackathonRequest hackathonRequest, Hackathon hackathon) {
		if (hackathonRequest.getJudges() == null || hackathonRequest.getJudges().isEmpty()) {
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
		List<HackathonTeamProfile> teams = hackathon.getTeams();

		if (teams.isEmpty() || !hackathonRequest.isFinalized()) {
			hackathon.setFinalized(hackathonRequest.isFinalized());
		} else {
			boolean canFinalize = true;
			Map<String, List<HackathonTeamProfile>> hackathonTeamProfilemap = teams.stream()
					.collect(Collectors.groupingBy(team -> {
						String name = team.getHackathonTeamProfile();
						name = name.substring(0, name.lastIndexOf(OHConstants.DELIMIT_HACK_TEAM_PROFILE));
						return name;
					}));
			for (Entry<String, List<HackathonTeamProfile>> teamEntry : hackathonTeamProfilemap.entrySet()) {
				if (teamEntry.getValue().stream().anyMatch(team -> {
					return team.isPaid() && team.getScore() == null;
				})) {
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

	private void mapBasicHackathonRequestParam(HackathonRequest hackathonRequest, Hackathon hackathon)
			throws ParseException {
		hackathon.setOpen(hackathonRequest.isOpen());
		hackathon.setDescription(hackathonRequest.getDescription());
		hackathon.setDiscount(hackathonRequest.getDiscount());
		hackathon.setEndDate(hackathonRequest.getEndDate());
		hackathon.setRegistrationFee(hackathonRequest.getRegistrationFee());
		hackathon.setStartDate(hackathonRequest.getStartDate());
		hackathon.setTeamMaxSize(hackathonRequest.getTeamMaxSize());
		hackathon.setTeamMinSize(hackathonRequest.getTeamMinSize());
	}

	@Override
	public List<Hackathon> retrieveHackathon(String email, String role) throws ParseException {
		Profile profile = profileManagementService.getProfile(email);
		if (profile == null) {
			throw new BadRequestException("user with given email doesn't exist");
		}
		if (profile.isAmdin() && !role.equals(OHConstants.ADMIN_ROLE)) {
			throw new BadRequestException("user with given email doesn't have role " + role);
		}

		if (role.equals(OHConstants.ADMIN_ROLE)) {
			List<Hackathon> hackathons = new ArrayList<>();
			hackathonRepository.findAll().forEach(hackathons::add);
			return hackathons;
		} else if (role.equals(OHConstants.HACKER_ROLE)) {
			DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = inputFormatter.parse(inputFormatter.format(new Date()));
			String currentDate = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date);
			List<String> hackathons = hacathonTeamProfileRepository.findHackathonByProfile(email);
			hackathons.add("###");
			return hackathonRepository.findHackathonBeforeStartAndNameIn(currentDate, hackathons);
		} else if (role.equals(OHConstants.JUDGE_ROLE)) {
			return profile.getHackathonJudge();
		} else {
			throw new BadRequestException("invalid role value");
		}
	}

	@Override
	public Quotation fetchQuotation(String email, String eventName) {
		Quotation quotation = new Quotation();
		Profile profile = profileManagementService.getProfile(email);
		if (profile == null) {
			throw new BadRequestException("User Profile does not exist");
		}
		Organization profileOrganization = profile.getOrganization();
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("Hackathon event does not exist");
		}
		Hackathon hackathon = hackathonWrapper.get();
		boolean organizationPresent = false;

		HackathonTeamProfile hackathonTeamProfile = hacathonTeamProfileRepository.findByHackathonAndProfile(eventName,
				email);
		if (profile.getOrganizationApprovalStatus() && profileOrganization != null) {
			String profileOrganizationName = profileOrganization.getName();
			List<Organization> hackathonSponsors = hackathon.getSponsors();
			for (Organization sponsor : hackathonSponsors) {
				if (sponsor.getName().equals(profileOrganizationName)) {
					organizationPresent = true;
					break;
				}
			}
		}
		float hackathonFee = hackathon.getRegistrationFee();
		float discountPercentage = 0;
		float discount = 0;
		if (organizationPresent) {
			discountPercentage = hackathon.getDiscount();
			discount = (float) ((hackathonFee * discountPercentage) / 100.0);
		}
		float discountedPrice = hackathonFee - discount;
		quotation.setDiscount(discount);
		quotation.setDiscountedPrice(discountedPrice);
		quotation.setDiscountPercent(discountPercentage);
		quotation.setOriginalPrice(hackathonFee);
		quotation.setEventName(eventName);
		if (hackathonTeamProfile != null) {
			quotation.setTeamName(hackathonTeamProfile.getTeamName());
		}
		quotation.setMessage("Success");
		quotation.setSuccess(true);
		return quotation;
	}

	@Override
	public Hackathon retrieveHackathonDetail(String email, String role, String eventName) throws ParseException {
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("hackathon doesn't exist");
		}
		Profile profile = profileManagementService.getProfile(email);
		if (profile == null) {
			throw new BadRequestException("user with given email doesn't exist");
		}
		if (profile.isAmdin() && !role.equals(OHConstants.ADMIN_ROLE)) {
			throw new BadRequestException("user with given email doesn't have role " + role);
		}

		Hackathon hackathon = hackathonWrapper.get();
		if (role.equals(OHConstants.ADMIN_ROLE)) {
			return hackathon;
		} else if (role.equals(OHConstants.HACKER_ROLE)) {
			if (hackathon.getJudges().stream().anyMatch(judge -> judge.getEmail().equals(email))) {
				throw new BadRequestException("user is a judge for this event");
			} else {
				return hackathon;
			}
		} else if (role.equals(OHConstants.JUDGE_ROLE)) {
			if (hackathon.getJudges().stream().anyMatch(judge -> judge.getEmail().equals(email))) {
				return hackathon;
			} else {
				throw new BadRequestException("user is not a judge for this event");
			}
		} else {
			throw new BadRequestException("invalid role value");
		}
	}

	public List<HackathonTeamProfile> retrieveHackathonDetailJudge(String email, String role, String eventName)
			throws ParseException {
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("hackathon doesn't exist");
		}
		Profile profile = profileManagementService.getProfile(email);
		if (profile == null) {
			throw new BadRequestException("user with given email doesn't exist");
		}
		if (profile.isAmdin() && !role.equals(OHConstants.ADMIN_ROLE)) {
			throw new BadRequestException("user with given email doesn't have role " + role);
		}

		Hackathon hackathon = hackathonWrapper.get();
		if (role.equals(OHConstants.JUDGE_ROLE)) {
			if (hackathon.getJudges().stream().anyMatch(judge -> judge.getEmail().equals(email))) {
				return hacathonTeamProfileRepository.findByHackathon(hackathon);
			} else {
				throw new BadRequestException("user is not a judge for this event");
			}
		} else {
			throw new BadRequestException("invalid role value");
		}
	}

	@Override
	public boolean makePayment(Quotation quotation) throws ParseException {
		boolean result = true;
		String eventName = quotation.getEventName();
		String email = quotation.getEmail();
		HackathonTeamProfile hackathonTeamProfile = hacathonTeamProfileRepository.findByHackathonAndProfile(eventName,
				email);
		if (hackathonTeamProfile == null) {
			throw new BadRequestException("Hackathon team profile does not exist");
		}
		if (hackathonTeamProfile.isPaid()) {
			result = false;
			throw new BadRequestException("User has already made payment");
		}
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("Hackathon event does not exist");
		}
		hackathonTeamProfile.setPaid(true);
		hacathonTeamProfileRepository.save(hackathonTeamProfile);
		return result;

	}

	public boolean submitCode(String teamName, String eventName, String url) throws ParseException {
		boolean validationsPassed = true;
		List<HackathonTeamProfile> hackathonTeamProfiles = hacathonTeamProfileRepository
				.findByHackathonAndTeam(eventName, teamName);
		boolean notRegistered = false;
		for (HackathonTeamProfile hackathonProfile : hackathonTeamProfiles) {
			if (!hackathonProfile.isPaid()) {
				notRegistered = true;
				break;
			}
		}
		if (notRegistered) {
			validationsPassed = false;
			throw new BadRequestException("All team members have not yet completed registration !!");
		}
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("Hackathon event does not exist");
		}
		Hackathon hackathon = hackathonWrapper.get();

		if (!hackathon.isOpen()) {
			validationsPassed = false;
			throw new BadRequestException(
					"Event has not been opened for submissions, urls can only be submitted after hackathon is open");
		}
		if (validationsPassed) {
			for (HackathonTeamProfile hackathonProfile : hackathonTeamProfiles) {
				if (hackathonProfile.isLead()) {
					hackathonProfile.setSubmission(url);
					hacathonTeamProfileRepository.save(hackathonProfile);
				}
			}
		}
		return validationsPassed;

	}

	@Override
	@Transactional
	public boolean gradeHackathon(GradeRequest gradeRequest) {
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(gradeRequest.getEventName());
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("hackathon doesn't exist");
		}
		Profile profile = profileManagementService.getProfile(gradeRequest.getJudge());
		if (profile == null) {
			throw new BadRequestException("user with given email doesn't exist");
		}
		if (profile.isAmdin()) {
			throw new BadRequestException("user with given email is not a judge");
		}

		Hackathon hackathon = hackathonWrapper.get();
		if (hackathon.isOpen()) {
			throw new BadRequestException("hackathon is currently open can't grade now");
		}
		if (hackathon.getJudges().stream().anyMatch(judge -> judge.getEmail().equals(gradeRequest.getJudge()))) {
			Map<String, TeamProfileResponse> grades = gradeRequest.getTeams().stream()
					.collect(Collectors.toMap(t -> t.getTeamName(), t -> t));
			List<HackathonTeamProfile> teams = hacathonTeamProfileRepository.findByHackathon(hackathon);
			for (HackathonTeamProfile team : teams) {
				team.setScore(grades.get(team.getTeamName()).getScore());
				hacathonTeamProfileRepository.save(team);
			}
			return true;
		} else {
			throw new BadRequestException("user is not a judge for this event");
		}

	}

	@Override
	public SortedMap<Float, Map<String, List<HackathonTeamProfile>>> retrieveLeaderBoardTeams(String eventName) {
		Optional<Hackathon> hackathonWrapper = hackathonRepository.findByEventName(eventName);
		if (!hackathonWrapper.isPresent()) {
			throw new BadRequestException("hackathon doesn't exist");
		}
		Hackathon hackathon = hackathonWrapper.get();
		if (!hackathon.isFinalized()) {
			throw new BadRequestException("hackathon is yet to be finalized");
		}
		List<HackathonTeamProfile> teams = hacathonTeamProfileRepository.findByHackathon(hackathon);
		return teams.stream().collect(Collectors.groupingBy(team -> team.getScore()==null?-1:team.getScore(),
				() -> new TreeMap<>(Collections.reverseOrder()), Collectors.groupingBy(team -> team.getTeamName())));
	}
}

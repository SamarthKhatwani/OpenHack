package sjsu.edu.cmpe275.api.service.intefaces;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;
import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.resources.GradeRequest;
import sjsu.edu.cmpe275.api.resources.HackathonRequest;
import sjsu.edu.cmpe275.api.resources.Quotation;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;

public interface IHackathonManagementService {

	public Hackathon createOrUpdateHackathon(HackathonRequest hackathonRequest) throws ParseException;

	public boolean registerUserHackathon(TeamRegisterRequest teamRegisterRequest) throws ParseException;

	public List<Hackathon> retrieveHackathon(String email, String role) throws ParseException;

	public Hackathon retrieveHackathonDetail(String email, String role, String eventName) throws ParseException;

	public Quotation fetchQuotation(String email, String eventName);
	
	public boolean makePayment(Quotation quotation) throws ParseException;
	
	public boolean submitCode(String teamName, String eventName, String url) throws ParseException;

	public List<HackathonTeamProfile> retrieveHackathonDetailJudge(String email, String role, String eventName) throws ParseException;
	
	public boolean gradeHackathon(GradeRequest grradeRequest);
	
	public SortedMap<Float, Map<String, List<HackathonTeamProfile>>> retrieveLeaderBoardTeams(String eventName);
}

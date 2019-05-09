package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;

public interface HacathonTeamProfileRepository extends CrudRepository<HackathonTeamProfile, String> {
	
	@Query(value="SELECT h.hackathon_id from hackathon_team_profile h where h.hacker= ?1", nativeQuery=true)
	public List<String> findHackathonByProfile(String email);
	
	@Query(value="SELECT * from hackathon_team_profile h where h.hackathon_id = ?1 and h.hacker= ?2", nativeQuery=true)
	public HackathonTeamProfile findByHackathonAndProfile(String eventName, String email);
	


	public List<HackathonTeamProfile> findByTeamName(String teamName);

	@Query(value="SELECT * from hackathon_team_profile h where h.hackathon_id = ?1 and h.team_name= ?2", nativeQuery=true)
	public List<HackathonTeamProfile> findByHackathonAndTeam(String eventName, String teamName);


}

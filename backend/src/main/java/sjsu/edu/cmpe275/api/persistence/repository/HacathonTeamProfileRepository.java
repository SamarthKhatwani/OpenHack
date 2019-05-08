package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;

public interface HacathonTeamProfileRepository extends CrudRepository<HackathonTeamProfile, String> {
	
	@Query(value="SELECT h.hackathon_id from hackathon_team_profile h where h.hacker= ?1", nativeQuery=true)
	public List<String> findHackathonByProfile(String email);
}

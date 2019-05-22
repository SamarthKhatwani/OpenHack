package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;

public interface HackathonRepository extends CrudRepository<Hackathon, Long> {

	public Optional<Hackathon> findByEventName(String eventName);

	@Query(value = "SELECT * FROM hackathon h WHERE ( h.start_date >= ?1 or h.event_name in ?2 ) and h.event_name not in ?3", nativeQuery=true)
	public List<Hackathon> findHackathonBeforeStartAndNameIn(Date date, List<String> names, List<String> exclusion);
	
	@Query(value="SELECT h.hackathon_id from hackathon_judge h where h.judge_id= ?1", nativeQuery=true)
	public List<String> findHackathonByJudge(String email);
	
	@Query(value="SELECT h.judge_id from hackathon_judge h where h.hackathon_id= ?1", nativeQuery=true)
	public List<String> findJudgeByHackathon(String eventName);
	
	
}

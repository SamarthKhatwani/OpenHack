package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;

public interface HackathonRepository extends CrudRepository<Hackathon, Long> {

	public Optional<Hackathon> findByEventName(String eventName);

	@Query(value = "SELECT * FROM hackathon h WHERE h.start_date >= ?1 or h.event_name in ?2", nativeQuery=true)
	public List<Hackathon> findHackathonBeforeStartAndNameIn(Date date, List<String> names);
	
}

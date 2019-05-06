package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.Hackathon;

public interface HackathonRepository extends CrudRepository<Hackathon, Long> {
	public Optional<Hackathon> findByEventName(String eventName);
}

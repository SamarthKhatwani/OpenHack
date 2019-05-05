package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

	@Transactional(readOnly = true)
	public Optional<Profile> findByEmail(String email); 
}

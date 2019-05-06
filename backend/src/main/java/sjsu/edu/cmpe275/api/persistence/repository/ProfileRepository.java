package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

	@Transactional(readOnly = true)
	public Optional<Profile> findByEmail(String email);

	@Transactional(readOnly = true)
	public Optional<Profile> findByScreenName(String screenName); 
	
	@Transactional(readOnly = true)
	public List<Profile> findByEmailIn(List<String> emails);
}

package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	@Transactional(readOnly = true)
	public Optional<Organization> findByName(String name);

	@Transactional(readOnly = true)
	public List<Organization> findByNameContaining(String name);
	
	@Transactional(readOnly =true)
	public List<Organization> findByNameIn(List<String> names);
	
	@Transactional(readOnly =true)
	@Query(value="SELECT * FROM organization o WHERE o.owner = ?1", nativeQuery=true)
	public List<Organization> findByOwnerEmail( String email);
}

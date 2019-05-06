package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	@Transactional(readOnly = true)
	public Optional<Organization> findByName(String name);
	
	//create select name from organization where name like '%name%'
	//@Query("SELECT o FROM Organization o WHERE o.name LIKE CONCAT('%',:name,'%')")
	@Transactional(readOnly = true)
	public List<Organization> findByNameContaining(String name);
}

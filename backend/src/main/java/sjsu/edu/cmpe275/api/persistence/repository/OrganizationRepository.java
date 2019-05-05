package sjsu.edu.cmpe275.api.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sjsu.edu.cmpe275.api.persistence.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	@Transactional(readOnly = true)
	public Optional<Organization> findByName(String name);
}

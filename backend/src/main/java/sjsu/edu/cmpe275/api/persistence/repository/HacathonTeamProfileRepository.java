package sjsu.edu.cmpe275.api.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;

public interface HacathonTeamProfileRepository extends CrudRepository<HackathonTeamProfile, String> {

}

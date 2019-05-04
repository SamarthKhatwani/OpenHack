package sjsu.edu.cmpe275.api.repository;

import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}

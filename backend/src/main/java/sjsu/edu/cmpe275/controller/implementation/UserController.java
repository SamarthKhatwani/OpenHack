package sjsu.edu.cmpe275.controller.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sjsu.edu.cmpe275.api.model.User;
import sjsu.edu.cmpe275.api.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/users", produces= MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@RequestMapping(value="/users/{userId}", produces= MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public Optional<User> getAllUsers(@PathVariable long userId) {
		return  userRepository.findById(userId);
	}
	
	@RequestMapping(value="/user", produces= MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return  userRepository.save(user);
	}

}

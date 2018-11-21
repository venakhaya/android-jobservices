package com.usermanager.app.controllers;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanager.app.domain.MessageResponse;
import com.usermanager.app.domain.User;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody Object saveUser(@RequestBody User user) {
		if (user != null) {
			if (isValidUserData(user)) {
				return userRepository.save(user);
			}
		}
		return new MessageResponse("Failed to persist user, check your input fields");
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateUser(@PathVariable(value = "id") Long id, @RequestBody User user,
			@RequestHeader HttpHeaders headers) {
		User userToUpate = userRepository.getOne(id);
		if (isValidUserData(user)) {
			userToUpate.setFirstName(user.getFirstName());
			userToUpate.setLastName(user.getLastName());
			return userRepository.save(userToUpate);
		}
		return new MessageResponse("Failed to update user, your input fields");
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody MessageResponse deleteUser(@PathVariable(value = "id") Long id,
			@RequestHeader HttpHeaders headers) {
		User userTodelete = userRepository.getOne(id);
		if (userTodelete == null) {
			return new MessageResponse("User not found");
		} else {
			userTaskRepositoryQueryManager.deletByUserId(id);
			userRepository.deleteById(id);
		}
		return new MessageResponse("Successfull deleted");
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public@ResponseBody Object getUser(@PathVariable(value = "id") Long id, @RequestHeader HttpHeaders headers) {
		User user = userRepository.getOne(id);
		if(user!= null) {
			return user;
		}
		return new MessageResponse("User not found");
	}

	private boolean isValidUserData(User user) {
		return user.getFirstName() != null && user.getFirstName().length() > 0 && user.getLastName() != null
				&& user.getLastName().length() > 0 && user.getUsername() != null && user.getUsername().length() > 0;
	}
}

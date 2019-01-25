package pl.lukaszgrymulski.app.ws.ui.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import pl.lukaszgrymulski.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users/
public class UserController {
	
	@GetMapping //bind this method to HTTP request
	public UserRest getUsers(
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort
			) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail("test@test.com");
		returnValue.setFirstName("Tommy");
		returnValue.setLastName("Versetti");
		returnValue.setUserId("1wD15gG2gE");
		return returnValue;
	}
	
	@GetMapping(path="/{userId}", 
				produces= {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
				}) 
	public String getUser(@PathVariable String userId) {
		return "get user was called with userId = " + userId;
	}
	
	@PostMapping 
	public String createUser() {
		return "create user was called";
	}
	
	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
	
}

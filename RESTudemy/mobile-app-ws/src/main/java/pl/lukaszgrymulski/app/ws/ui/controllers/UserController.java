package pl.lukaszgrymulski.app.ws.ui.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.lukaszgrymulski.app.ws.exceptions.UserServiceException;
import pl.lukaszgrymulski.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import pl.lukaszgrymulski.app.ws.ui.model.request.UserDetailsRequestModel;
import pl.lukaszgrymulski.app.ws.ui.model.response.UserRest;
import pl.lukaszgrymulski.app.ws.userservice.UserService;

@RestController
@RequestMapping("users") // http://localhost:8080/users/
public class UserController {
	
	Map <String, UserRest> users; //just to simulate DB without having one
	
	@Autowired
	UserService userService;
	
	
	@GetMapping //bind this method to HTTP request
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort) 
	{
		return "get users was called with page = " + page 
				+ ", limit = " + limit
				+ ", sort = " + sort;
	}
	
	@GetMapping(path="/{userId}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) 
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		//if (true) throw new UserServiceException("A user service exception is thrown");		
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
				produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = userService.createUser(userDetails); 
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", 
				consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
				produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		users.put(userId, storedUserDetails);
		return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
	
}

package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.portal.bean.User;
import com.portal.service.UserService;




@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String emailId, @RequestParam() String str) {
		
		User user = userService.getUser(emailId);
		
		if (user ==null) {
			return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
	
	
	
	@RequestMapping (value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String emailId, @RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		User isExist = userService.getUser(emailId);
		if(isExist == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} 
		else if (user == null) {
			return new ResponseEntity<User> (HttpStatus.BAD_REQUEST);
		}
		
		userService.updateUser(user);
		headers.add("User Updated - ", String.valueOf(emailId));
		return new ResponseEntity<User> (user, headers, HttpStatus.OK);
	}
	
	
	

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <User> createUserAccount(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		 
		if(user == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		userService.createUser(user);
		headers.add("User Created - ", String.valueOf(user.getEmailId()));
		return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String emailId) {
		HttpHeaders headers = new HttpHeaders();
		
		User user = userService.getUser(emailId);
		if(user == null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUser(emailId);
		headers.add("User Deleted - ", emailId);
		return new ResponseEntity<User> (user, headers, HttpStatus.NO_CONTENT);
	}
	
	
	
}

package co.psyke.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.models.User;
import co.psyke.responses.UserSearchResponse;
import co.psyke.services.UserService;


@RestController
@RequestMapping("user")
@CrossOrigin(originPatterns = "*")
@Validated
public class UserController {

	@Autowired
	private UserService us; 

	@PostMapping("add")
	public ResponseEntity<Long> addUser(@RequestBody @Valid User user){
		Long l = us.addUser(user);

		return new ResponseEntity<Long>(l, HttpStatus.OK);
	}

	@PostMapping("delete")
	public ResponseEntity<Boolean> deleteUser(@RequestParam @NotEmpty Long id){
		Boolean removed = us.deleteUser(id);

		// NOTE THAT: returning the value false does not necessarily mean that there is an error
		//  but it can mean that there is no user with that id
		return new ResponseEntity<Boolean>(removed, HttpStatus.OK);
	}
	
	@PostMapping("update")
	public ResponseEntity<Boolean> updateUser(@RequestBody @Valid User user){
		Boolean updated = us.updateUser(user); 

		// NOTE THAT: returning the value false does not necessarily mean that there is an error
		//  but it can mean that the older user is the same that the newest one
		return new ResponseEntity<Boolean>(updated, HttpStatus.OK);
	}

	@GetMapping("get")
	public ResponseEntity<User> getUser(@RequestParam @NotEmpty Long id){
		User user = us.getUser(id); 

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("search")
	public ResponseEntity<UserSearchResponse> searchUser (@RequestParam @NotEmpty String searchFilter){
		UserSearchResponse usr = us.selectUsers(searchFilter); 
		return new ResponseEntity<UserSearchResponse>(usr, HttpStatus.OK);
	}

}

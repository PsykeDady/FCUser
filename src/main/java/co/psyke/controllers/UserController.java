package co.psyke.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.models.User;
import co.psyke.responses.UserSearchResponse;
import co.psyke.services.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*")
@Validated
public class UserController {

	@Autowired
	private UserService us; 

	@GetMapping("/")
	public ResponseEntity<List<User>> list(){
		List<User> l = us.list();

		return ResponseEntity.ok().body(l);
	}

	@PostMapping("add")
	public ResponseEntity<Long> addUser(@RequestBody @Valid User user){
		Long l = us.addUser(user);

		return new ResponseEntity<Long>(l, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable @NotNull Long id){
		us.deleteUser(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("update")
	public ResponseEntity<Boolean> updateUser(@RequestBody @Valid User user){
		Boolean updated = false;  
		try {
			updated=us.updateUser(user);
		} catch (IllegalStateException ie) {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}

		
		return new ResponseEntity<Boolean>(updated, HttpStatus.OK);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<User> getUser(@PathVariable @NotNull Long id){
		User user = us.getUser(id); 

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("search/{searchFilter}")
	public ResponseEntity<UserSearchResponse> searchUser (@PathVariable @NotEmpty String searchFilter){
		UserSearchResponse usr = us.selectUsers(searchFilter); 
		return new ResponseEntity<UserSearchResponse>(usr, HttpStatus.OK);
	}

}

package co.psyke.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.models.User;
import co.psyke.repositories.UserRepository;
import co.psyke.responses.UserSearchResponse;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur; 

	public List<User> list() {
		return ur.findAll(); 
	}

	public Long addUser (User user){
		User response = ur.save(user);	

		return response.id;
	}

	public boolean deleteUser(Long id){
		if (! ur.existsById(id)) {
			return false;
		}
		ur.deleteById(id);
		return !ur.existsById(id);
	}

	public boolean updateUser(User user){
		if(ur.existsById(user.id)){
			return false;
		}
		User updatedUser= ur.save(user); 
		return updatedUser.equals(user);
	}

	/**
	 * Search user by id
	 * @param id
	 * @return the User requested or a new Empty one, if cannot find any user with the given id
	 */
	public User getUser(Long id){
		return ur.findById(id).orElse(new User());
	}

	public UserSearchResponse selectUsers(String searchFilter) {
		UserSearchResponse usr=new UserSearchResponse();

		List<User> allName=ur.findByNameSimilarities(searchFilter);
		usr.setByName(allName);

		List<User> allLast=ur.findByLastNameSimilarities(searchFilter);
		usr.setByLastName(allLast);

		List<User> allAddr=ur.findByAddressSimilarities(searchFilter);
		usr.setByAddress(allAddr);

		return usr;
	}
}

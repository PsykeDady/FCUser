package co.psyke.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.psyke.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u from User u LIKE :name")
	List<User> findByNameSimilarities(String name);

	@Query("Select u from User u LIKE :lastname")
	List<User> findByLastNameSimilarities(String lastname);
	
	@Query("Select u from User u LIKE :address")
	List<User> findByAddressSimilarities(String address);

}

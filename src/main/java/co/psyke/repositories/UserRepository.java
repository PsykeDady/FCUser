package co.psyke.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.psyke.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.nome LIKE %:name%")
	List<User> findByNameSimilarities(String name);

	@Query("SELECT u FROM User u WHERE u.cognome LIKE %:lastname%")
	List<User> findByLastNameSimilarities(String lastname);

	@Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
	List<User> findByEmailSimilarities(String email);

	@Query("SELECT u FROM User u WHERE u.indirizzo LIKE %:address%")
	List<User> findByAddressSimilarities(String address);

}

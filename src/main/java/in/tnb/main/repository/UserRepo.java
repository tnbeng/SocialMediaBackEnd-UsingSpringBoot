package in.tnb.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.tnb.main.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("Select u from User u where u.firstname LIKE %:query% OR u.lastname LIKE %:query% ")//OR u.useremail LIKE %:query% to search by email
	List<User> searchUser(@Param("query") String keyword);
	
	User findByUseremail(String useremail);

}

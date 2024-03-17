package in.tnb.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.User;
import in.tnb.main.repository.UserRepo;

@RestController
public class UserController {
	
	@Autowired
	UserRepo repo;
	@GetMapping("/")
	public String openIndex()
	{
		return "Welome";
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return repo.findAll();
	}
	@GetMapping("/users/search/{key}")
	public List<User> searchUserByKeyword(@PathVariable("key") String keyword)
	{
		return repo.searchUser(keyword);
	}
	
	@GetMapping("/users/{userid}")
	public User findUserByUserId(@PathVariable("userid") int id) throws Exception
	{
		Optional<User> user=repo.findById(id);
		
		if(user.isPresent())
		{
			return user.get();
		}
		throw new Exception("User not found with userid "+id);
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user)
	{
		return repo.save(user);
	
	}
	
	@PutMapping("/update/{userid}")
	public User updateUser(@RequestBody User user,@PathVariable("userid") int id) throws Exception
	{
		Optional<User> user_ref=repo.findById(id);
		if(user_ref.isEmpty())
		{
			throw new Exception("User not found with userid "+id);
		}
		User exist_user=user_ref.get();
		
		if(user.getFirstname()!=null)
		{
			exist_user.setFirstname(user.getFirstname());
		}
		if(user.getLastname()!=null)
		{
			exist_user.setLastname(user.getLastname());
		}
		if(user.getUseremail()!=null)
		{
			exist_user.setUseremail(user.getUseremail());
		}
		if(user.getUserpassword()!=null)
		{
			exist_user.setUserpassword(user.getUserpassword());
		}
		
		
		return repo.save(exist_user);
		
	}
	
	@DeleteMapping("/delete/{userid}")
	public String deleteUser(@PathVariable("userid") int id)
	{
		repo.deleteById(id);
		return "User deleted successfully with userid"+id;
	}
	
}

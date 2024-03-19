package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.User;
import in.tnb.main.repository.UserRepo;
import in.tnb.main.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepo repo;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String openIndex()
	{
		return "Welome";
	}
	
	@PostMapping("/login")
	public AuthResponse loginUser(@RequestParam("email") String email,@RequestParam("password") String password) throws Exception
	{
		
       return userService.loginUser(email, password);
	}
	@PostMapping("/register")
	public AuthResponse createUser(@RequestBody User user) throws Exception
	{
		return userService.createUser(user);
	}

	
	@PutMapping("/secure/updateUser")
	public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User exist_user=userService.findUserFromToken(jwt);

		return userService.updateUser(user, exist_user);
		
	}
	
	@DeleteMapping("/secure/deleteUser")
	public String deleteUser(@RequestHeader("Authorization") String jwt)
	{
		User exist_user=userService.findUserFromToken(jwt);
		return userService.deleteUser(exist_user.getUserid());
	}
	//This should be for admin only
	@GetMapping("/secure/users")
	public List<User> findAllUsers()
	{
		return userService.findAllUser();
	}
 	//This should be for admin only
	@GetMapping("/secure/users/{userid}")
	public User findUserByUserId(@PathVariable("userid") int id) throws Exception
	{
		return userService.findUserByUserId(id);
	}
	
	@GetMapping("/secure/users/search/{key}")
	public List<User> searchUserByKeyword(@PathVariable("key") String keyword)
	{
		return userService.searchUserByKeyword(keyword);
	}
	
	
	@GetMapping("/secure/followUser/{userid}")
	public User followUser(@RequestHeader("Authorization") String jwt,@PathVariable("userid") int id) throws Exception
	{
		User user1=userService.findUserFromToken(jwt);
	    User user2=userService.findUserByUserId(id);
	    User following_usre=userService.followUser(user1, user2);
	    
	    return following_usre;
	}
	
}

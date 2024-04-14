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

import in.tnb.main.models.LoginData;
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
	@GetMapping("/secure")
	public String check()
	{
		return "Welome after cheking jwt";
	}
	
	@PostMapping("/signin")
	public AuthResponse loginUser(@RequestBody LoginData loginData) throws Exception
	{
		System.out.println("User password "+loginData.getUserpassword());
		System.out.println("User email "+loginData.getUseremail());
       return userService.loginUser(loginData.getUseremail(), loginData.getUserpassword());
	}
	@PostMapping("/signup")
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
	@GetMapping("/users")
	public List<User> findAllUsers()
	{
		return userService.findAllUser();
	}
 	//This should be for admin only
	@GetMapping("/secure/users/profile")
	public User findUserByUserId(@RequestHeader("Authorization") String jwt) throws Exception
	{   
		User user=userService.findUserFromToken(jwt);
		return userService.findUserByUserId(user.getUserid());
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

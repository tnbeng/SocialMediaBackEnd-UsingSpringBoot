package in.tnb.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import in.tnb.main.controllers.AuthResponse;
import in.tnb.main.models.User;
import in.tnb.main.repository.UserRepo;
import in.tnb.main.securityconfig.JwtProvider;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public User findUserByUserId(int id) throws Exception
	{
		Optional<User> user=userRepo.findById(id);
		
		if(user.isPresent())
		{
			return user.get();
		}
		throw new Exception("User not found with userid "+id);
	}
	@Override
	public User findUserFromToken(String jwt) {
		String email=JwtProvider.getEmailFormToken(jwt);
		
		User user=userRepo.findByUseremail(email);
		
		return user;
	}
	
	@Override
	public AuthResponse createUser(User user) throws Exception {
		
        User exist_user=userRepo.findByUseremail(user.getUseremail());
		
		if(exist_user!=null)
		{
			throw new Exception("This email is already used with another accound ");
		}
		
		User created_user=userRepo.save(user);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(created_user.getUseremail(),created_user.getUserpassword());
		String jwt=JwtProvider.generateToken(authentication);
		return new AuthResponse("User created successfully", jwt);
	}
	@Override
	public AuthResponse loginUser(String email,String password) throws Exception {
        System.out.println("Email "+email+" Password "+password);
		UserDetails userDetails=userDetailsService.loadUserByUsername(email);
		
		if(passwordEncoder.matches(password, userDetails.getPassword()))
		{
		  Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		  String jwt=JwtProvider.generateToken(authentication);
		  System.out.println("Jwt "+jwt);
		 return new AuthResponse("Login success",jwt);
		}
		else
		{
		  throw new Exception("Password did not match");
		}
	}
	
	
	@Override
	public User updateUser(User user, User exist_user ) throws Exception {
		if(exist_user==null)
		{
			throw new Exception("User not found");
		}
		
		
		if(user.getFirstname()!=null)
		{
			exist_user.setFirstname(user.getFirstname());
		}
		if(user.getLastname()!=null)
		{
			exist_user.setLastname(user.getLastname());
		}
		if(user.getGender()!=null)
		{
			exist_user.setGender(user.getGender());
		}
		if(user.getUseremail()!=null)
		{
			exist_user.setUseremail(user.getUseremail());
		}
		if(user.getUserpassword()!=null)
		{
			exist_user.setUserpassword(user.getUserpassword());
		}
		
		
		return userRepo.save(exist_user);
	}
	@Override
	public String deleteUser(int id) {
		userRepo.deleteById(id);
		return "User deleted successfully with userid"+id;
	}
	@Override
	public List<User> findAllUser() {
		return userRepo.findAll();
	}
	@Override
	public List<User> searchUserByKeyword(String keyword) {
		return userRepo.searchUser(keyword);
	}
	
	@Override
	public User followUser(User user1,User user2) {
		
		
	   user2.getFollowers().add(user1.getUserid());
	   user1.getFollowings().add(user1.getUserid());
	   User following_user=userRepo.save(user1);
	   userRepo.save(user2);
		
	   return following_user;
	}
	
}

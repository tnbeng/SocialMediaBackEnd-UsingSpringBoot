package in.tnb.main.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import in.tnb.main.controllers.AuthResponse;
import in.tnb.main.models.User;

public interface UserService {
	
	public User findUserByUserId(int id) throws Exception;
	public User findUserFromToken(String jwt);
	
	public AuthResponse createUser(@RequestBody User user) throws Exception;
	public AuthResponse loginUser(String email,String password) throws Exception;
	
    public User updateUser(User user,User exist_user) throws Exception;
    public String deleteUser(int id);
    public List<User> findAllUser();
    public List<User> searchUserByKeyword(String keyword);
    
	public User followUser(User user1,User user2);
	
}

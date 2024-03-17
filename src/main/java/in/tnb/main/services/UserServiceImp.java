package in.tnb.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.User;
import in.tnb.main.repository.UserRepo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepo userRepo;
	public User findUserByUserId(int id) throws Exception
	{
		Optional<User> user=userRepo.findById(id);
		
		if(user.isPresent())
		{
			return user.get();
		}
		throw new Exception("User not found with userid "+id);
	}
}

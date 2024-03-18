package in.tnb.main.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.tnb.main.repository.UserRepo;
import in.tnb.main.services.UserService;


//if we don't want to use autogenerated default password by spring security
//We have to set user password to spring security  and thats why we have to override loaduserbyusernaem function that comes under UserDetailsService interface and also there is UserDetails interface that implements User and finaly we can set User.userpassword that comes after username function of User
@Service
public class UserDetailsServiceImple implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		in.tnb.main.models.User exsit_user=userRepo.findByUseremail(username);
		
		if(exsit_user==null)
		{
			throw new UsernameNotFoundException("User not fond with :"+username);
		}
		
		UserDetails user=User//.withUsername("anything")//we can give anything here it does not matter just we have to user Withusername()function because password comes after username 
				             //We basically find user by username and extract userpassword and set it to password field of inbuild User Class and apring security matches it's set password  with entered password
				             .withUsername(exsit_user.getUseremail())
				             .password(exsit_user.getUserpassword())
				             .build();
		return user;
	}

}

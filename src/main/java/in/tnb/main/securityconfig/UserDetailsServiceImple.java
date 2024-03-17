package in.tnb.main.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.tnb.main.services.UserService;

public class UserDetailsServiceImple implements UserDetailsService {
    @Autowired
    UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		in.tnb.main.models.User exsit_user=null;
		try {
			exsit_user=userService.findUserByUserId(Integer.parseInt(username));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		UserDetails user=User.withUsername(username)
				             .password(exsit_user.getUserpassword())
				             .build();
		return user;
	}

}

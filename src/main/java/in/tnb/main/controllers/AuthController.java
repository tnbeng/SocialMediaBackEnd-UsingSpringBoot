package in.tnb.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.securityconfig.JwtProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public AuthResponse  userLogin(@RequestParam("email") String email,@RequestParam("password") String password) throws Exception
	{
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(email);
		
		if(passwordEncoder.matches(password, userDetails.getPassword()))
		{
		  Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		  String jwt=JwtProvider.generateToken(authentication);
		  
		 return new AuthResponse("Login success",jwt);
		}
		else
		{
		  throw new Exception("Password did not match");
		}
	}

}

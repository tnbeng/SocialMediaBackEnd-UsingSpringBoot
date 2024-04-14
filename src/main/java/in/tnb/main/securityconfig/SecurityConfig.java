package in.tnb.main.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
	
		http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(Authorize-> Authorize
				.requestMatchers("/secure/**").authenticated()
				.anyRequest().permitAll()) 
		    
		    //.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)// if i want both jwt validation and basic auth work that means users can authenticated by either by jwt or by basic auth 
		    
		    //before basic authentication we are validating jwt 
		    .addFilterBefore(new JwtAuthenticationFilter(),BasicAuthenticationFilter.class)//The addFilterBefore() method in Spring Security is used to insert a custom filter into the security filter chain before a specified filter class. This allows you to add custom security-related logic to the filter chain at a specific position.
			//Filter Chain: The security filter chain in Spring Security consists of a series of filters that are executed in a specific order. These filters handle tasks such as authentication, authorization, CSRF protection, etc. By adding your custom filter before a specific filter in the chain, you can customize the behavior of the security configuration.
		    //addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class) inserts a JwtValidator filter before the BasicAuthenticationFilter in the filter chain. This allows the application to validate JWT tokens before the basic authentication filter processes the request.
		     
		    .csrf(csrf->csrf.disable());
		    
			//.httpBasic();//it is not required since we are authenticating by jwt and that is before BasicAuthentication
	 
		return http.build();
	}
	
	//We can create bean here or we can anoted with @service on the class that implements UserDetailsService
//	@Bean 
//	UserDetailsService userDetailsService()
//	{
//		return new UserDetailsServiceImple();
//	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
	   return NoOpPasswordEncoder.getInstance();
	}

}

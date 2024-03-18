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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
	
		http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(Authorize-> Authorize
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll())        
		    .addFilterBefore(new JwtValidator(),BasicAuthenticationFilter.class)
			.csrf(csrf->csrf.disable())
			.httpBasic();
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

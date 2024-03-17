package in.tnb.main.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(Authorize-> Authorize
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll())
			.csrf(csrf->csrf.disable())
			.httpBasic();
	  return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImple();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
	   return NoOpPasswordEncoder.getInstance();
	}

}

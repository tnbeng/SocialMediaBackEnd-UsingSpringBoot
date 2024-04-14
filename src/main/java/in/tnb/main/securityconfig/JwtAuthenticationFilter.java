package in.tnb.main.securityconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	 public static String jwt_header="Authorization";
	 public static String secret_key="joueofujouoewurorhjugutdhytuuegdtwusiytwheutwfsgshs";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt=request.getHeader("Authorization");
	
		if(jwt != null && isValidToken(jwt) )
		{
			
			try
			{
				
				String email=JwtProvider.getEmailFormToken(jwt);
				
				List<GrantedAuthority> authorities=new ArrayList<>();
				Authentication authentication=new UsernamePasswordAuthenticationToken(email,null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);// we must write this to inform spring security that the request is authenticated
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
		filterChain.doFilter(request, response);//This line passes the request and response objects to the next filter in the chain. It allows the request to continue processing after the JWT validation/authentication logic has been applied.
		
		
	}
	
	
	
	private boolean isValidToken(String jwt) {
		
		if (jwt.startsWith("Bearer "))
		{
            jwt= jwt.substring(7);
        }
		
	    try {
	        // Parse the token
	        Claims claims = Jwts.parserBuilder()
	                            .setSigningKey(JWTConstant.key)
	                            .build()
	                            .parseClaimsJws(jwt)
	                            .getBody();
	        
	        // Check expiration
	        Date expiration = claims.getExpiration();
	        if (expiration == null || expiration.before(new Date())) {
	            return false; // Token has expired
	        }
	        
	        // Additional custom checks if needed
	        
	        return true; // Token is valid
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return false; // Token is invalid or malformed
	    }
	}

	
	
	
	

	
}

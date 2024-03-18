package in.tnb.main.securityconfig;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtProvider {
    private static SecretKey key=Keys.hmacShaKeyFor(JWTConstant.secret_key.getBytes());
    public static String generateToken(Authentication auth)//we have to chose Authentication core
    {
    	String jwt=Jwts.builder()
    			       .setIssuer("Tarak")
    			       .setIssuedAt(new Date())
    			       .setExpiration(new Date(new Date().getTime()+86400000))
    			       .claim("email", auth.getName())
    			       .signWith(key)
    			       .compact();
    	return jwt;
    }
    
    public static String getEmailFormToken(String jwt)
    {
    	jwt=jwt.substring(7);
    	
    	Claims claims=Jwts.parserBuilder()
    			      .setSigningKey(key).build()
    			      .parseClaimsJws(jwt)
    			      .getBody();
    	
    	String email=String.valueOf(claims.get("email"));
    			
    			return email;
    }
}

package in.tnb.main.securityconfig;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtProvider {
    
    
   //generate jwt token
    public static String generateToken(Authentication auth)//we have to chose Authentication core
    {
    	String jwt=Jwts.builder()
    			       .setIssuer("Tarak")
    			       .setIssuedAt(new Date())//current date
    			       .setExpiration(new Date(new Date().getTime()+86400000))// a data and extended with 24 hours ,86400000 this is equivalent to 24 hours
    			       .claim("email", auth.getName())// we can set multiple claim also
    			       .signWith(JWTConstant.key)  //In the JJWT library, the signWith() method expects a cryptographic key (either a SecretKey or a PrivateKey), not a string value.
    			       .compact();
  
    	return jwt;
    	
    }
    
    //extract email from jwt 
    public static String getEmailFormToken(String jwt)
    {
 
    	if (jwt.startsWith("Bearer "))
		{
            jwt= jwt.substring(7);
        }
    	
    	Claims claims=Jwts.parserBuilder()
    			      .setSigningKey(JWTConstant.key).build()//we have set key again because it parseClaimsJws method verify this key with extracted key from jwt
    			      .parseClaimsJws(jwt)//separate its components, including the header, payload (claims), and signature. After parsing, the method verifies the integrity and authenticity of the JWT by checking its signature against the provided key (setSigningKey(key) in the code). If the signature is valid, it indicates that the token has not been tampered with and comes from a trusted source.
    			      .getBody();//This method returns an object (often referred to as Claims object) representing the decoded claims of the JWT.
    	
    	String email=String.valueOf(claims.get("email")); //This line extracts the "email" claim from the JWT token's claims. The get("email") method returns the value associated with the "email" claim. If the claim is not present, it returns null. String.valueOf() is used to convert the value to a String.
    			
    			return email;
    }
}

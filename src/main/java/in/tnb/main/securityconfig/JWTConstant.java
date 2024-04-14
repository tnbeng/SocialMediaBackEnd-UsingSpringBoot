package in.tnb.main.securityconfig;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class JWTConstant {
    public static String jwt_header="Authorization";
    public static String secret_key="joueofujouoewurorhjugutdhytuuegdtwusiytwheutwfsgshs";
    
    public static SecretKey key=Keys.hmacShaKeyFor(JWTConstant.secret_key.getBytes());//getBytes return byte array with byte values,here every byte value is corresponded to every character ASCII value
}

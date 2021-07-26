package jwt;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AppUtil {

	public static final byte[] SECRET = "mYsecmYsemYsmYsecretmYsemYsecretKEYcretKEYKEYecretKEYcretKEYretKEY".getBytes();
	
	public static String generateToken(Authentication authResult) {
		return Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .signWith(Keys.hmacShaKeyFor(SECRET))
                .compact();
	}
	
}

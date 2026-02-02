package com.paypal.user_service.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Notes:
//1. SECRET - A long string used as the JWT signing password.
//2. getSigningKey() - Converts the SECRET into a cryptographic key for signing and verifying tokens.
//3. extractEmail(token) - Verifies the token signature
//						   Extracts the subject (email) from inside the token. Used to identify the user from the token.
//4. validateToken(token, userName) - Tries to parse and verify the token
//									  If token is valid → returns true
//									  If token is tampered/expired → exception → false
//5. extractUsername(token)- Same as extractEmail() (both return the subject).
//							 This gives you the username/email stored inside the token.
//6. generateToken(claims, email) - Creates a new JWT:
//								setClaims(claims) → extra data you want inside token
//								setSubject(email) → main user identity
//								setIssuedAt() → time token was created
//								setExpiration() → expiry (here, 24 hours)
//								signWith() → signs token with your secret key
//								.compact() → builds final JWT string
//								This token is sent to the user after login
//⭐ One-line summary- JWTUtil signs tokens (generateToken), verifies tokens (validateToken), and extracts data (email/username) from them.

//Suppose you generate a token like this:
//Map<String, Object> claims = Map.of(
//    "role", "USER",
//    "userId", 101
//);
//
//String token = generateToken(claims, "shruti@gmail.com");

//The token’s decoded CLAIMS (payload) look like this:
//{
//  "role": "USER",              // custom claim
//  "userId": 101,               // custom claim
//  "sub": "shruti@gmail.com",   // subject (username/email)
//  "iat": 1714978500,           // issued at
//  "exp": 1715064900            // expiry time
//}

//Claims are simply key-value data stored inside the token, like a small JSON.

@Component
public class JWTUtil {

	private static final String SECRET = "secret123secret123secret123secret123secret123secret123";
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public Boolean validateToken(String token, String userName) {
		try {
			extractEmail(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
   public String generateToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // Still keeping email as subject for backward compatibility
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

	public String extractRole(String token) {
		return (String) Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role");
	}


}


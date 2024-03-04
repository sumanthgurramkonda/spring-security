package com.pro.SpringSecurityJWTAuth.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private String SECRETE_KEY="Secrete";
	
	public String exctractUsername(String tocken) {
		return extractClaim(tocken,Claims::getSubject);
	}
	
	public Date extractExpiration(String tocken) {
		return extractClaim(tocken,Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String tocken) {
		return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJws(tocken).getBody();
	}
	
	public Boolean isTockenExpired(String tocken) {
		return extractExpiration(tocken).before(new Date());
	}
	
	public String generateTocken(UserDetails userDetails) {
		Map<String,Object> claims =new HashMap();
		return createTocken(claims,userDetails.getUsername());
	}
	
	public String createTocken(Map<String,Object> calims,String subject) {
		return Jwts.builder().setClaims(calims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+2*60*1000))
				.signWith(SignatureAlgorithm.HS256, SECRETE_KEY).compact();
	}
	
	public Boolean validateTocken(String tocken,UserDetails userDatails) {
		final String username = exctractUsername(tocken);
		return (username.equals(userDatails.getUsername()) && !isTockenExpired(tocken));
	}

}








package com.minhlv.socialappapi.security;

import com.minhlv.socialappapi.service.impl.CustomUserDetailsImpl;
import com.minhlv.socialappapi.utils.Utils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

	public final String JWT_SECRET_DEV = Base64.getEncoder().encodeToString("123456".getBytes());
	public final String JWT_SECRET_PROD = Base64.getEncoder().encodeToString(Utils.randomString(30).getBytes());
	private final long JWT_EXPIRATION_DEV = 365 * 24 * 60 * 60 * 1000L; // limit 01 year
	private final long JWT_EXPIRATION_PROD = 1 * 24 * 60 * 60 * 1000L; // limit 01 days
	@Autowired
	private Environment environment;

	public String getSecret() {
		if (environment != null && !environment.getActiveProfiles()[0].equals("dev")) {
			return JWT_SECRET_PROD;
		} else {
			return JWT_SECRET_DEV;
		}
	}

	public long getExpiration() {
		if (environment != null && !environment.getActiveProfiles()[0].equals("dev")) {
			return JWT_EXPIRATION_PROD;
		} else {
			return JWT_EXPIRATION_DEV;
		}
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String generateJwtToken(Authentication authentication) {
		CustomUserDetailsImpl userPrincipal = (CustomUserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + getExpiration()))
				.signWith(SignatureAlgorithm.HS512, getSecret()).compact();
	}

	public String generateToken(UserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + getExpiration());
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, getSecret()).compact();
	}

	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}

}

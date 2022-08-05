package com.minhlv.socialappapi.secury;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.minhlv.socialappapi.utils.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

    @Autowired
    private Environment environment;
    public final String JWT_SECRET_DEV = Base64.getEncoder().encodeToString("123456".getBytes());
    public final String JWT_SECRET_PROD = Base64.getEncoder().encodeToString(Utils.randomString(30).getBytes());
    private final long JWT_EXPIRATION_DEV = 365 * 24 * 60 * 60 * 1000L; // limit
                                                                        // 01
                                                                        // year
    private final long JWT_EXPIRATION_PROD = 7 * 24 * 60 * 60 * 1000L; // limit
                                                                       // 07
                                                                       // days
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

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + getExpiration());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder().setSubject(userDetails.getUser().getUsername()).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, getSecret()).compact();
    }

    public String generateToken(UserDetails userDetails) {
        // Lấy thông tin user
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + getExpiration());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, getSecret()).compact();
    }

    // Lấy thông tin user từ jwt
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public String getUsernameFromJWTUnisign(String token) {
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

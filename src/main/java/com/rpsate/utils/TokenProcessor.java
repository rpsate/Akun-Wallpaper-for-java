package com.rpsate.utils;

import com.rpsate.pojo.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class TokenProcessor {
    public static String Authorization = "Authorization";

    /**
     * generate token
     * @param id
     * @param username
     * @return
     */
    public static String generateToken(Long id, String username, Integer grade, String secret) {
        Date nowTime =  DateTimeProcessor.local2date(LocalDateTime.now());
        Date expirationTime = DateTimeProcessor.local2date(LocalDateTime.now().plusDays(7));
        Claims claims = Jwts.claims();
        claims.setSubject(username);
        claims.put("id", id);
        claims.put("grade", grade);
        claims.setNotBefore(nowTime);
        claims.setExpiration(expirationTime);
        claims.setIssuedAt(nowTime);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret);
        return jwtBuilder.compact();
    }

    /**
     * verify token
     * @param token
     * @return
     */
    public static User getUserFromToken(String token, String secret) {
        Claims claims = Jwts.claims();
        claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        Long id = claims.get("id", Long.class);
        Integer grade = claims.get("grade", Integer.class);
        return new User(id ,username, null, grade);
    }

    public static String getTokenFromBearer(String bearer) {
        if (bearer == null || !bearer.startsWith("Bearer") || bearer.length() < 7)
            return null;
        //截取开头的[Bearer ]
        return bearer.substring(7);
    }
}

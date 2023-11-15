package com.ucatolica.easyevent.easyevent.JWT;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "WebToken";

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return claims.getBody().getSubject() != null;
        } catch (JwtException e) {
            return false;
        }
    }

}

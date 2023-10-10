package mye.fisio.mak.apimak.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String jwtExpirationMs;

    //Crear token
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationMs)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Obtener firma del token
    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    //Validar el token de acceso
    public boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            System.out.println("Error en el token: " + e.getMessage());
            return false;
        }
    }

    //Obtener el nombre de usuario del token
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    //Obtener el un claim generico del token
    public <T> T getClaim(String token, Function<Claims, T> claims){
        Claims claims2 = getClaims(token);
        return claims.apply(claims2);
    }

    //Obtener el cuerpo del token
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

}

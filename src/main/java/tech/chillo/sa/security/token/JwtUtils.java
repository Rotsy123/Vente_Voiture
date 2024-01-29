package tech.chillo.sa.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import javax.naming.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static String secret = "Thisissecret14568751378963743654368fffffffffffffffffffffffffffff7";
    private static long expiryDuration = 3600000;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public String generateJwt(UserDetails user) {

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 10000000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(String.valueOf(user.getUsername()))
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);
    
        // Ajouter le rôle de l'utilisateur aux claims
        claims.put("role", user.getAuthorities().iterator().next().getAuthority());
    
        // optional claims
        // claims.put("email", user.getUsername());
        // claims.put("admin", user.g);

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims verify(String token) throws Exception {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new AccessDeniedException("Access denied");// TODO: handle exception
        }
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return verify(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getCause().getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            req.setAttribute("invalid", e.getMessage());
        }
        return null;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public String parseToken(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}

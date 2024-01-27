// package tech.chillo.sa.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.stereotype.Component;
// import tech.chillo.sa.entites.Personne;

// import java.util.Date;

// @Component
// public class TokenUtil {
//     private static final String SECRET_KEY = "MinoSipaTsyManjaSatriaTsyManaikyManjaFaMainty";
//     private static final long EXPIRATION_TIME = 10 * 60 * 1000; //

//     public boolean isTokenValid(String token) throws BadCredentialsException {
//         try {
//             System.out.println("Validating token: " + token); // Instruction de débogage
            
//             // Déchiffrer le token et obtenir les claims (payload)
//             Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

//             // Vérifier la date d'expiration (exp) dans les claims
//             long expirationTime = claims.getExpiration().getTime();
//             return expirationTime >= System.currentTimeMillis(); // Vérifie si la date d'expiration est postérieure à la
//                                                                  // date actuelle
//         } catch (ExpiredJwtException e) {
//             // Le token a expiré
//             throw new BadCredentialsException("Token expired", e);
//         } catch (Exception e) {
//             // Une erreur s'est produite lors de la vérification du token
//             throw new BadCredentialsException("Invalid token", e);
//         }
//     }

//     public static String generateToken(Personne personne) {
//         System.out.println("Generating token for user: " + personne.getId()); // Instruction de débogage
        
//         return Jwts.builder()
//                 .setSubject(String.valueOf(personne.getId())) // ID de l'utilisateur
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }
// }

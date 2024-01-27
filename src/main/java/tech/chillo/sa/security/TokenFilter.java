// package tech.chillo.sa.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.web.filter.OncePerRequestFilter;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;


// import java.io.IOException;

// public class TokenFilter extends OncePerRequestFilter {

//     private final TokenUtil tokenUtil;

//     public TokenFilter(TokenUtil tokenUtil) {
//         this.tokenUtil = tokenUtil;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String token = extractToken(request);

//         if (token != null && tokenUtil.isTokenValid(token)) {
//             // Créez une authentification pré-authentifiée avec le token et définissez-la dans le contexte de sécurité
//             Authentication  auth = new UsernamePasswordAuthenticationToken(token, null);
//             SecurityContextHolder.getContext().setAuthentication(auth);
//         } else {
//             // Si le token est invalide, renvoyez une réponse d'erreur 401 Unauthorized
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }

//     private String extractToken(HttpServletRequest request) {
//         // Extraire le token de l'en-tête Authorization
//         String header = request.getHeader("Authorization");
//         if (header != null && header.startsWith("Bearer ")) {
//             return header.substring(7);
//         }
//         return null;
//     }
// }



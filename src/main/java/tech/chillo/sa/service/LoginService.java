package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tech.chillo.sa.entites.Compte;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.CompteRepository;
import tech.chillo.sa.repository.PersonneRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
// import carselling.selling.exception.UserException;
// import carselling.selling.repository.UserRepository;
import tech.chillo.sa.response.ApiResponse;
import tech.chillo.sa.security.token.JwtUtils;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    JwtUtils jwtUtils;

    public Compte connected(String mail, String motdepasse) throws Exception {
        Optional<Personne> personne = personneRepository.findByMail(mail);

        if (personne.isPresent()) {
            Personne user = personne.get();
            Compte c = compteRepository.findByPersonne_Mail(mail)
                    .orElseThrow(() -> new NoSuchElementException("Personne introuvable"));
            if (motdepasse.equalsIgnoreCase(c.getMotdepasse()))
                return c;
            throw new Exception("Mot de passe incorrect");
        }

        throw new Exception("Mail incorrect");
    }

    public ApiResponse login(Compte user) {
        ApiResponse response = new ApiResponse();
        String password = user.getMotdepasse();
        try {
            Optional<Personne> personne = personneRepository.findByMail(user.getPersonne().getMail());
            if (!personne.isPresent()) {
                response.addError("email", "This account doesn't exist.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return response;
            }
            connected(user.getPersonne().getMail(), password);
            // response.addData("token", jwtUtils.generateJwt(user));
        } catch (Exception e) {
            // TODO: handle exception
            response.addData("error", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Compte userOptional = compteRepository.findByPersonne_Mail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Personne introuvable"));

            // Construire la liste des rôles de l'utilisateur (sans ajouter manuellement le
            // préfixe "ROLE_")
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userOptional.getPersonne().getRole()));

            // Construire et retourner l'objet UserDetails
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(userOptional.getPersonne().getId()))
                    .password(userOptional.getMotdepasse())
                    .authorities(authorities) // Utilisation de "authorities" au lieu de "roles"
                    .build();

        } catch (UsernameNotFoundException ex) {
            throw ex; // Laisser UsernameNotFoundException être propagée
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Une erreur s'est produite lors de la recherche de l'utilisateur", ex);
        }
    }

}

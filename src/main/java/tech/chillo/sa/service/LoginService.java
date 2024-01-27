package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
// import carselling.selling.exception.UserException;
// import carselling.selling.repository.UserRepository;
import tech.chillo.sa.response.ApiResponse;
import tech.chillo.sa.security.token.JwtUtils;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private PersonneRepository userRepository;
    @Autowired
    private PersonneService userService;
    @Autowired
    JwtUtils jwtUtils;

    public ApiResponse login(Personne user) {
        ApiResponse response = new ApiResponse();
        String password = user.getMotdepasse();
        try {
            Optional<Personne> personne = userRepository.findByMail(user.getMail());
            if (!personne.isPresent()) {
                response.addError("email", "This account doesn't exist.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return response;
            }
            userService.connected(user.getMail(), password);
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
        // TODO Auto-generated method stub
        // System.out.println("ETTTTTTTTTTTTTTTTTTOOOOOOOO");
        Optional<Personne> useroptional = userRepository.findByMail(email);
        // System.out.println("ETTTTTTTTTTTTTTTTTTOOOOOOOO");
        List<String> roles = new ArrayList<>();
        if (!useroptional.isPresent()) {
            throw new UsernameNotFoundException("Check your mail");
        }
        System.out.println("tayyyyyyyyyy");
        Personne user = useroptional.get();
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder()
                .username(String.valueOf(user.getId()))
                .password(user.getMotdepasse())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}

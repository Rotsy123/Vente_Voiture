package tech.chillo.sa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;

import tech.chillo.sa.service.PersonneService;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableMethodSecurity
public class SecurityConfig  {

    // @Autowired
    // private TokenUtil tokenUtil;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http // Désactiver CSRF pour simplifier l'exemple, à activer en production
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers( "/login/**").permitAll()
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable) // Toutes les autres
                                                       // requêtes
                                                       // nécessitent une
        // authentification
        ;
        return http.build();
        // Autres configurations de sécurité...
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean
    // public PersonneService personneService () {
    //     return new PersonneService();
    // }

    // @Bean
    // public AuthenticationProvider authenticationProvider () {
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //     daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
    //     daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
    //     return daoAuthenticationProvider;

    // }
}

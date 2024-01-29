package tech.chillo.sa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tech.chillo.sa.security.filter.TokenFilter;
import tech.chillo.sa.service.LoginService;

@Configuration
@EnableMethodSecurity
public class WebSecurity {
    @Autowired
    LoginService loginService;
    @Autowired
    TokenFilter tokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(loginService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .requestMatchers(
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/compte/sign"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/annonce/listeannonce"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/compte"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/annonce/annoncevalidee"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/voiture")
                )
                .permitAll()
                .requestMatchers("/.*") // Chemin dynamique pour tous les GET
                    .access("@authService.checkRole(request)")    
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http.csrf().disable()
    // .cors().and()
    // .authorizeRequests()
    // .requestMatchers(HttpMethod.POST, "/compte/sign").permitAll()
    // .requestMatchers(HttpMethod.POST, "/compte").permitAll()
    // .requestMatchers(HttpMethod.GET, "/statistique").hasRole("ADMIN") //
    // Configuration pour l'accès avec le rôle ADMIN seulement
    // .anyRequest().authenticated()
    // .and()
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and()
    // .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

    // return http.build();
    // }

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}

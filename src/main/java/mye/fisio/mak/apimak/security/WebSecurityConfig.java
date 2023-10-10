package mye.fisio.mak.apimak.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import mye.fisio.mak.apimak.data.ClientDetailImpl;
import mye.fisio.mak.apimak.security.filters.JwtAuthFilter;
import mye.fisio.mak.apimak.security.filters.JwtAutorizationFilter;
import mye.fisio.mak.apimak.security.jwt.JwtUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ClientDetailImpl clientDetailImpl;

    @Autowired
    JwtAutorizationFilter jwtAutorizationFilter;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(clientDetailImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager() {
        return authentication -> authenticationProvider().authenticate(authentication);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtUtils);
        jwtAuthFilter.setAuthenticationManager(authManager);
        jwtAuthFilter.setFilterProcessesUrl("/fisiomak/api/v1/oauth");

        return http
                .csrf(conf -> conf.disable())
                .cors(conf -> {
                    conf.configurationSource(request -> {
                        var cors = new org.springframework.web.cors.CorsConfiguration();
                        cors.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
                        cors.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        cors.setAllowedHeaders(java.util.List.of("*"));
                        cors.setAllowCredentials(true);
                        return cors;
                    });
                })
                .authorizeHttpRequests(auth -> {
                    // auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients").permitAll();// Para que no pida autenticación para el GET de clients
                    // auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients").hasAnyRole("admin", "user"); // Para varios roles
                    // auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients").hasRole("admin"); // Para un solo rol

                    //Administrador
                    auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients").hasRole("admin");
                    auth.requestMatchers(HttpMethod.POST, "/fisiomak/api/v1/clients").hasRole("admin");
                    auth.requestMatchers(HttpMethod.PUT, "/fisiomak/api/v1/clients").hasRole("admin");
                    auth.requestMatchers(HttpMethod.DELETE, "/fisiomak/api/v1/clients").hasRole("admin");

                    auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/comments").hasRole("admin");
                    auth.requestMatchers(HttpMethod.POST, "/fisiomak/api/v1/comments").hasRole("admin");
                    auth.requestMatchers(HttpMethod.PUT, "/fisiomak/api/v1/comments").hasRole("admin");
                    auth.requestMatchers(HttpMethod.DELETE, "/fisiomak/api/v1/comments").hasRole("admin");

                    //Clientes no autenticados
                    auth.requestMatchers(HttpMethod.POST, "/fisiomak/api/v1/register", "/fisiomak/api/v1/register/").permitAll();
                    
                    // Clientes autenticados
                    auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients").hasAnyRole("admin", "user");
                    auth.requestMatchers(HttpMethod.GET, "/fisiomak/api/v1/clients/username/").hasAnyRole("admin", "user");
                   
                    
                    // Para que pida autenticación para cualquier otra petición
                    auth.anyRequest().authenticated(); 
                })
                .formLogin(form -> form.disable())
                .sessionManagement(conf -> {
                    conf.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                    conf.maximumSessions(1);
                    conf.sessionFixation().migrateSession();
                })
                .addFilter(jwtAuthFilter)
                .addFilterAfter(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

}

package com.helpdesk.HelpDesk.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.helpdesk.HelpDesk.security.JWTAuthenticatorFilter;
import com.helpdesk.HelpDesk.security.JWTAuthorizationFilter;
import com.helpdesk.HelpDesk.security.JWTUtil;


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**", "/*"};

	@Autowired
    private Environment env;
	
	@Autowired
    private JWTUtil jwtUtil;
	
	@Autowired
    private UserDetailsService detailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration)
            throws Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
        	http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        }
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .addFilter(new JWTAuthenticatorFilter(authConfiguration.getAuthenticationManager(), jwtUtil))
        .addFilter(new JWTAuthorizationFilter(authConfiguration.getAuthenticationManager(), detailsService, jwtUtil))
        .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.requestMatchers(PUBLIC_MATCHERS).permitAll())
        .authorizeHttpRequests(noAuthorize -> noAuthorize.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

package com.krdl.task13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthProperties authProperties;

    public SecurityConfig(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
            
                .requestMatchers(
                    AppConstants.SWAGGER_UI_WILDCARD,
                    AppConstants.SWAGGER_UI_HTML,
                    AppConstants.API_DOCS_WILDCARD
                ).permitAll()

               
                .anyRequest().permitAll()
            );

        // BASIC AUTH 
        if (AppConstants.AUTH_METHOD_BASIC.equalsIgnoreCase(authProperties.getMethod())) {
            http.httpBasic(Customizer.withDefaults());
        }

        // API KEY FILTER 
        if (AppConstants.AUTH_METHOD_API_KEY.equalsIgnoreCase(authProperties.getMethod())) {
            http.addFilterBefore(
                new ApiKeyAuthFilter(
                    authProperties.getApiKey().getHeader(),
                    authProperties.getApiKey().getValue()
                ),
                UsernamePasswordAuthenticationFilter.class
            );
        }

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        if (!AppConstants.AUTH_METHOD_BASIC.equalsIgnoreCase(authProperties.getMethod())) {
            return new InMemoryUserDetailsManager();
        }

        return new InMemoryUserDetailsManager(
            User.withUsername(authProperties.getBasic().getUsername())
                .password(AppConstants.PASSWORD_PREFIX_NOOP + authProperties.getBasic().getPassword())
                .roles(AppConstants.USER_ROLE)
                .build()
        );
    }
}
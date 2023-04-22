package com.dummy.vessel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfigFilterChain {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean  // First thing Spring security looks for is this bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login") //  URL where the application should POST the credentials entered on the login page for authentication.
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // sets the URL pattern that triggers the logout process when accessed.
                                .permitAll()
                );
        http.authenticationProvider(authenticationProvider);

        return http.build();
    }
}

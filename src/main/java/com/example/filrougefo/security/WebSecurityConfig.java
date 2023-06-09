package com.example.filrougefo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
    private ClientDetailServiceImpl clientDetailService;
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(clientDetailService);
        return provider;
    }
    @Bean
    @SessionScope
    public ClientAuthDetail authenticatedClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof ClientAuthDetail) {
            return (ClientAuthDetail) authentication.getPrincipal();
        } else {
            return null;
        }
    }
    private static final String[] WHITELIST_RESSOURCES = {"/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico"};
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(WHITELIST_RESSOURCES).permitAll();
                })
                .authorizeHttpRequests(req -> {
                    req
                         //.requestMatchers("/auth/**").authenticated()
                         .requestMatchers("/products/**").permitAll()
                         .requestMatchers("/client/register").permitAll()
                         .requestMatchers("/login").permitAll()
                         .requestMatchers("/logout").permitAll()
                         .anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form
                            .loginPage("/login")
                            .usernameParameter("email")
                            .passwordParameter("password")

                            .defaultSuccessUrl("/products", true);
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/auth/logout")
                            .permitAll()
                            .logoutSuccessUrl("/login");
                })
                .build();
    }
}

package com.example.filrougefo.security;

import com.example.filrougefo.entity.Client;
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
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception {

        return httpSecurity
                .authorizeHttpRequests(req -> {
                    req
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/categories/*").permitAll()
                    .requestMatchers("/client/register").permitAll()
                    .anyRequest().authenticated();

                })
                .formLogin(form -> {
                    form
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/categories", true);
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout")
                            .permitAll()
                            .logoutSuccessUrl("/login");
                })
                .build();
    }
}

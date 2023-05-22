package com.example.filrougefo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                    .requestMatchers("/products").permitAll()
                    .requestMatchers("/client/register").permitAll()
                    .requestMatchers("/").permitAll();

                })
                .formLogin(form -> {
                    form
                            .loginPage("/login")
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

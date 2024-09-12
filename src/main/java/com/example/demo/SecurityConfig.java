package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeHttpRequests(
            customizer -> customizer
                .requestMatchers("**").permitAll())
        .httpBasic(Customizer.withDefaults())
        .userDetailsService(users())
        .csrf(customizer -> customizer.disable())
        .build();
  }

  @Bean
  public UserDetailsService users() {
    return new InMemoryUserDetailsManager(
        Collections.singletonList(
            User.withDefaultPasswordEncoder()
                .username("test")
                .password("test")
                .build()
        )
    );
  }
}

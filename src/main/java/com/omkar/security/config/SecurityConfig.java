package com.omkar.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      return  http.authorizeHttpRequests(auth -> auth.requestMatchers("/student").hasRole("student")
              .requestMatchers("/teacher").hasRole("teacher")
              .requestMatchers("principal").hasRole("principal")
              .anyRequest().permitAll())
              .httpBasic(httpBasic->{})
              .csrf(AbstractHttpConfigurer::disable)
              .build();

    }





    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}

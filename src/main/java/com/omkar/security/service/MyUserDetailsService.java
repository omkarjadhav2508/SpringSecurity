package com.omkar.security.service;

import com.omkar.security.model.MyUserDetails;
import com.omkar.security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    //adding comments to check git
    @Autowired
    private MyUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username);
    }

    public void addUser(MyUserDetails userDetails) {

        String authoritiesString = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)  // Extract the authority string from each GrantedAuthority
                .map(authority -> authority.replace("ROLE_", ""))
                .collect(Collectors.joining("::"));   // Join with your defined delimiter


        MyUserDetails userDetails1 = MyUserDetails.builder()
                .username(userDetails.getUsername())
                .password(passwordEncoder.encode(userDetails.getPassword()))
                .authorities(authoritiesString)
                .build();

        this.repository.save(userDetails1);


    }


}

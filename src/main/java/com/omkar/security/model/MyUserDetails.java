package com.omkar.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUserDetails implements UserDetails {

    private static final String delem="::";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    private String authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /*Collection<GrantedAuthority> grantedAuthorities= new ArrayList<>();
         Arrays.stream(this.authorities.split(delem)).map(a-> new SimpleGrantedAuthority(a)).forEach(grantedAuthorities::add);
         return grantedAuthorities;*/

        return Arrays.stream(this.authorities.split("::"))
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority)) // Ensure ROLE_ prefix
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}

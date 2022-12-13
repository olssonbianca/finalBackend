package com.example.demo.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@EntityScan("entities")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name="user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private UserRol userRol;


    //Constructores

    public User(){

    }

    public User(String email, String password, UserRol userRol) {
        this.email = email;
        this.password = password;
        this.userRol = userRol;
    }



//Métodos interfaz

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRol.name());
        return Collections.singleton( grantedAuthority );

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return null;
    }
}

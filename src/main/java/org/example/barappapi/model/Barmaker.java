package org.example.barappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "barmaker")
public class Barmaker implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barmaker_id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "barmaker")
    @JsonIgnore
    private List<Cocktail> cocktails;

    @OneToMany(mappedBy = "barmaker")
    @JsonIgnore
    private List<Orders> orders;

    public Barmaker(Long barmaker_id, String name, String email, String password, List<Cocktail> cocktails, List<Orders> orders) {
        this.barmaker_id = barmaker_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cocktails = cocktails;
        this.orders = orders;
    }

    public Barmaker() {
    }

    public Barmaker(String email,String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return password;
    }
}

package com.grigoBiteUI.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grigoBiteUI.model.Transaction;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    @Nullable
    private String nickname;

    @JsonIgnore
    private String password;

    private String phoneNumber;

    private String role;

    private boolean active;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role.equals("ADMIN")) {
            return ApplicationUserRole.ADMIN.getGrantedAuthority();
        } else if (role.equals("PEMBELI")){
            return ApplicationUserRole.PEMBELI.getGrantedAuthority();
        } else {
            return ApplicationUserRole.PENJUAL.getGrantedAuthority();
        }
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Nullable
    private String profilePicture;
}

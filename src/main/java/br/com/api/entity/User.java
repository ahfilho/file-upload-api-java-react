package br.com.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "AUTH_USER_DETAILS")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME", unique = true)
    @NotNull
    private String userName;
    @Column(name = "USER_EMAIL")
    @NotNull
    private String email;

    @Column(name = "USER_CREATE")
    @NotNull
    private LocalDate userCreate;

    @Column(name = "USER_UPDATE")
    @NotNull
    private String userUpdate;

    @Column(name = "USER_PASSWORD")
    @NotNull
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

package br.com.api.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "AUTH_USER_AUTHORITY")
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "USER_DESCRIPTION")
    private String roleDescription;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}

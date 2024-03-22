package br.com.api.auth.service;

import br.com.api.auth.enumm.Role;
import br.com.api.auth.repository.UserDetailsRepository;
import br.com.api.auth.entity.Authority;
import br.com.api.auth.entity.User;
import br.com.api.auth.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository1;
    }

    public List<User> buscar() {
        return userDetailsRepository.findAll();
    }

    public void save(User user) {
        List<Authority> authorityList = new ArrayList<>();

        String lowercaseProfile = user.getProfile().toLowerCase();

        if ("admin".equals(lowercaseProfile)) {
            user.setProfile(Role.ROLE_ADMIN.getRole().toLowerCase());
            authorityList.add(createAuthority("ADMIN", "Admin role"));
        } else {
            if ("usuario".equals(lowercaseProfile)) {
                user.setProfile(Role.ROLE_USER.getRole().toLowerCase());
                authorityList.add(createAuthority("USER", "User role"));
            } else {
                throw new IllegalArgumentException("Perfil inválido:" + user.getProfile());
            }
            user.setEnabled(true);
            user.setAuthorities(authorityList);
        }
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    private Authority createAuthority(String rolecode, String roleDescription) {
        Authority authority = new Authority();
        authority.setRoleCode(rolecode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

    public List<User> listAll() {
//        if (authenticatedUser == null || isAdmin(authenticatedUser)) {
//            return userRepository.findAll();
//        } else {
//            return Collections.singletonList(authenticatedUser);
//        }
    return userRepository.findAll();
    }

    public boolean isAdmin(User user) {
        return "admin".equalsIgnoreCase(user.getProfile());
    }

}
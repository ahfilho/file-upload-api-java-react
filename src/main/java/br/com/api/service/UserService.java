package br.com.api.service;

import br.com.api.entity.User;
import br.com.api.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDetailsRepository userDetailsRepository;


    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }
    public List<User> buscar(){
        return userDetailsRepository.findAll();
    }
}

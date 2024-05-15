package br.com.api.auth.security;


import br.com.api.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void delete(Long id);

    @Query("SELECT u FROM User u WHERE u.cpf = ?1")
    User userWithSameCpf(String cpf);
}

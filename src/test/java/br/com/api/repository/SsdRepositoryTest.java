package br.com.api.repository;

import br.com.api.ApiApplication;
import br.com.api.ApiApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SsdRepositoryTest {

    @Autowired
    private ApiApplicationTests apiApplicationTests;
    @Test
    void dataDeVenda() {
    }
}
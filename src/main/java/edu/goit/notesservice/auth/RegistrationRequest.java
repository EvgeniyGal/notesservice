package edu.goit.notesservice.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class RegistrationRequest {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    //- додає нового юзера
    public void createUser(String name, String password) throws Exception {
        jdbcTemplate.update(
                "INSERT INTO users(name, password) VALUES (:name, :password)",
                Map.of("name", name, "password", password)
        );
    }
}

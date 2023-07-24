package edu.goit.notesservice.auth;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDTO {
    @Size(min = 5, max = 50, message = "Ім'я користувача має мати від 5 до 50 символів")
    private String username;

    @Size(min = 8, max = 100, message = "Пароль має мати від 8 до 100 символів")
    private String password;
}

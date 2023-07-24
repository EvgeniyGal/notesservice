package edu.goit.notesservice.auth;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDTO {
    @Size(min = 5, max = 50, message = "Username should be between 5 and 50 characters")
    private String username;

    @Size(min = 8, max = 100, message = "Password should be between 8 and 100 characters")
    private String password;
}

package edu.goit.notesservice.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class RegistrationValidator implements Validator {
    public final AuthService authService;
    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegistrationDTO registrationDTO = (RegistrationDTO) target;

        if (registrationDTO.getUsername().length() < 5 || registrationDTO.getUsername().length() > 50) {
            errors.rejectValue("username", "field.size", "Username should be between 5 and 50 characters.");
        }
        //Do not work. Why?
        if (authService.usernameExists(registrationDTO.getUsername())) {
            errors.rejectValue("username", "field.duplicated", "Username already exists.");
        }

        if (registrationDTO.getPassword().length() < 8 || registrationDTO.getPassword().length() > 100) {
            errors.rejectValue("password", "field.size", "Password should be between 8 and 100 characters.");
        }

    }
}

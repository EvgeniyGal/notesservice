package edu.goit.notesservice.auth;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required.");

        RegistrationDTO registrationDTO = (RegistrationDTO) target;

        if (registrationDTO.getUsername().length() < 5 || registrationDTO.getUsername().length() > 50) {
            errors.rejectValue("username", "field.size", "Username should be between 5 and 50 characters.");
        }

        if (registrationDTO.getPassword().length() < 8 || registrationDTO.getPassword().length() > 100) {
            errors.rejectValue("password", "field.size", "Password should be between 8 and 100 characters.");
        }
    }
}

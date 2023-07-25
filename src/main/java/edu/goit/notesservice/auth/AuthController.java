package edu.goit.notesservice.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private static final String REDIRECT_TO_LOGIN = "redirect:/login";
    private static final String REGISTRATION_FORM_LOCATION = "/security/registration";

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/security/login");
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        return new ModelAndView(REGISTRATION_FORM_LOCATION,
                "registrationDTO",
                new RegistrationDTO());
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(@Valid @ModelAttribute RegistrationDTO registrationDTO,
                                         BindingResult errors) {

        if (authService.isUsernameExists(registrationDTO.getUsername())) {
            errors.rejectValue("username", "field.duplicated", "Користувач з таким ім'ям вже існує.");
            return new ModelAndView(REGISTRATION_FORM_LOCATION, "registrationDTO", registrationDTO);
        }

        if (errors.hasErrors()) {
            return new ModelAndView(REGISTRATION_FORM_LOCATION);
        }

        authService.register(registrationDTO.getUsername(), passwordEncoder.encode(registrationDTO.getPassword()));
        return new ModelAndView(REDIRECT_TO_LOGIN);
    }
}

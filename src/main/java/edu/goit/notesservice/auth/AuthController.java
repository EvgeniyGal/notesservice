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

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/security/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegistration() {
        return new ModelAndView("/security/registration",
                "registrationDTO",
                new RegistrationDTO());
    }

    @PostMapping("/register")
    public ModelAndView postRegistration(@Valid @ModelAttribute RegistrationDTO registrationDTO,
                                         BindingResult errors) {

        if (authService.isUsernameExists(registrationDTO.getUsername())) {
            errors.rejectValue("username", "field.duplicated", "Користувач з таким ім'ям вже існує.");
            return new ModelAndView("/security/registration", "registrationDTO", registrationDTO);
        }

        if (errors.hasErrors()) {
            return new ModelAndView("/security/registration");
        }

        authService.register(registrationDTO.getUsername(), passwordEncoder.encode(registrationDTO.getPassword()));
        return new ModelAndView(REDIRECT_TO_LOGIN);
    }
}

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

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        return new ModelAndView("/security/registration", "registrationDTO", new RegistrationDTO());
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(@Valid @ModelAttribute RegistrationDTO registrationDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/security/registration").addObject("error", "Invalid username or password. Username should be between 5 and 50 characters, and password should be between 8 and 100 characters.");
        }

        authService.register(registrationDTO.getUsername(), passwordEncoder.encode(registrationDTO.getPassword()));
        return new ModelAndView(REDIRECT_TO_LOGIN);
    }

}

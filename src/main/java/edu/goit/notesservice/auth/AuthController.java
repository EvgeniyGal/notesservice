package edu.goit.notesservice.auth;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private static final String REDIRECT_TO_LOGIN = "redirect:/login";
    private static final String REDIRECT_TO_ERROR_REGPAGE = "redirect:/registration?error=true";

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/security/login");
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        return new ModelAndView("/security/registration");
    }

    @PostMapping("/registration")
    public String postRegistration(@Valid @RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password) {
        try {
            authService.register(username, passwordEncoder.encode(password));
            return REDIRECT_TO_LOGIN;
        } catch (AuthException e) {
            return REDIRECT_TO_ERROR_REGPAGE;
        }

    }
}

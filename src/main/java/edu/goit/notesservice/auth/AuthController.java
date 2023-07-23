package edu.goit.notesservice.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private static final String REDIRECT_TO_LIST = "redirect:/note/list";
    private static final String REDIRECT_TO_LOGIN = "redirect:/login";

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/security/login");
    }

    @PostMapping("/login")
    public String postLogin(@Valid @RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password) {

        System.out.println("Login");
        System.out.println(username);
        System.out.println(password);

        User user = authService.loadUserByUsername(username);
        System.out.println(user);
//        if (passwordEncoder.matches(passwordEncoder.encode(password), user.getPassword())) {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(name, null, user.getAuthorities())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);

            return REDIRECT_TO_LIST;
//        } else {
//            throw new BadCredentialsException("Невірний пароль чи ім'я користувача");
//        }
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        return new ModelAndView("/security/registration");
    }

    @PostMapping("/registration")
    public String postRegistration(@Valid @RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password) {

        System.out.println("registration");
        System.out.println(username);
        System.out.println(passwordEncoder.encode(password));

        authService.register(username, passwordEncoder.encode(password));
        return REDIRECT_TO_LOGIN;
    }

}

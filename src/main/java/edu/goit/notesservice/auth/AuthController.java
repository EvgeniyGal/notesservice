package edu.goit.notesservice.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public User defaultUser() {
        return new User();
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/security/login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "password") String password) {
        try {
            var user = userDetailsService.loadUserByUsername(name);
            String storedPassword = user.getPassword();
            if (passwordEncoder.matches(password, storedPassword)) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(name, null, user.getAuthorities())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return new ModelAndView("redirect:/note/list");
            }
        } catch (AuthenticationException e) {
            return new ModelAndView()
                    .addObject("error", true)
                    .addObject("errorMessage", e.getMessage());
        }
        return new ModelAndView("login")
                .addObject("errorMessage", true);
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        return new ModelAndView("/security/registration");
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(@RequestParam(name = "name") String name,
                                         @RequestParam(name = "password") String password) {
        try {
            userDetailsService.registration(name, password);
            return new ModelAndView("redirect:/note/list");
        } catch (AuthenticationException e) {
            return new ModelAndView()
                    .addObject("error", true)
                    .addObject("errorMessage", e.getMessage());
        }
    }

}

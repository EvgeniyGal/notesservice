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
    public ModelAndView getLogin(){
        return new ModelAndView("security/login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "password") String password) {
        try {
            var user = userDetailsService.loadUserByUsername(name);

            if (user != null) {
                String storedPassword = user.getPassword();

                if (passwordEncoder.matches(password, storedPassword)) {
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(name, password)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    return new ModelAndView("redirect:/list");
                }
            }
        } catch (AuthenticationException e) {
            ModelAndView modelAndView = new ModelAndView("security/errorLogin");
            modelAndView.addObject("exception", "Invalid username or password");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("security/errorLogin");
        modelAndView.addObject("exception", "Invalid username or password");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration(){
        return new ModelAndView("security/registration");
    }


}

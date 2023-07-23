package edu.goit.notesservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));
        return user;
    }

    public User register(String username, String password) {
        return userRepository.save(new User(username, password));
    }

    private Authentication getAuth() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    public User getUser() {
        String currentUsername = ((org.springframework.security.core.userdetails.User) getAuth().getPrincipal()).getUsername();
        return loadUserByUsername(currentUsername);
    }
}

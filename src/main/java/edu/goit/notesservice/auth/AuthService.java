package edu.goit.notesservice.auth;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository aRep;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationRequest registrationRequest;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = aRep.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public void registration(String name, String password){
        try {
            registrationRequest.createUser(name, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

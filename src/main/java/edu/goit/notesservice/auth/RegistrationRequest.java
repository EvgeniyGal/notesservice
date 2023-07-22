package edu.goit.notesservice.auth;


import edu.goit.notesservice.util.FileCreator;
import edu.goit.notesservice.util.VersionHolder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequest {
    //- додає нового юзера
    public void create(String name, String password) throws Exception {
        String statement = "INSERT INTO users (name, password) VALUES ('" + name + "', '" + password + "');";
        new FileCreator(new VersionHolder().getVersion(), statement);
    }
}

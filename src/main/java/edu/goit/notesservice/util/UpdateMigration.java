package edu.goit.notesservice.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateMigration {
    private final FlywayUtils flywayUtils;

    public void update() throws InterruptedException {
        flywayUtils.migrate();
    }
}

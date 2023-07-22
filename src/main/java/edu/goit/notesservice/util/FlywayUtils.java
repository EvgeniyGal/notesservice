package edu.goit.notesservice.util;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@Component
public class FlywayUtils {
    private Flyway flyway = Flyway.configure()
            .baselineOnMigrate(true)
            .dataSource("jdbc:h2:mem:testdb","sa","")
            .table("flyway_schema_history")
            .locations("/db/migration/dev")
            .load();

    public void migrate(){
        flyway.migrate();
    }
}

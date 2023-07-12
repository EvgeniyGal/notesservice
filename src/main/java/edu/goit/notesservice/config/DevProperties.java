package edu.goit.notesservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application_dev.properties")
@Profile("dev")
public class DevProperties {
}

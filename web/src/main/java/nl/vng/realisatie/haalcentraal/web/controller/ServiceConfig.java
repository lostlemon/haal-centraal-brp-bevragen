package nl.vng.realisatie.haalcentraal.web.controller;

import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * ServiceConfig
 */
@Configuration
public class ServiceConfig {

    @Setter
    private LocalDate localDate;

    public LocalDate getLocalDate() {
        if (localDate != null) {
            return localDate;
        } else {
            return LocalDate.now();
        }
    }

}

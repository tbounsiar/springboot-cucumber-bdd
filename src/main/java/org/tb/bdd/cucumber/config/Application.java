package org.tb.bdd.cucumber.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.tb.bdd.cucumber.config.core.DataConfig;
import org.tb.bdd.cucumber.config.core.WebfluxConfig;

@SpringBootApplication
@Import({WebfluxConfig.class, DataConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

package org.tb.bdd.cucumber.config.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = {"org.tb.bdd.cucumber.api"}
)
@EntityScan(
        basePackages = {"org.tb.bdd.cucumber.api"}
)
public class DataConfig {
}

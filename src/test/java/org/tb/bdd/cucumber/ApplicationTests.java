package org.tb.bdd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.tb.bdd.cucumber.config.Application;
import org.tb.bdd.cucumber.config.core.DataConfig;
import org.tb.bdd.cucumber.config.core.WebfluxConfig;

@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:report/index.json", "html:report/index.html"}, features = "classpath:feature")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {DataConfig.class, WebfluxConfig.class, Application.class})
@DirtiesContext
public class ApplicationTests {

}

package dev.ziriuz.webflux.demo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest(classes = SpringTestConfig.class)
public class CucumberConfig {
}

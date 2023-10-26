package dev.ziriuz.webflux.demo;

import org.junit.platform.suite.api.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite(failIfNoTests = false)
@EnableAutoConfiguration
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters(
        {
                @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "dev.ziriuz.webflux.demo"),
                @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:build/reports/cucumber-bdd-report.json, html:build/reports/cucumber-bdd-report.html"),
                @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:features"),
                @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @wip")
        }
)
@ExcludeTags({"ignore", "wip"})
public class RunCucumberTest {
}

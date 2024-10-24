package com.epam.gym.api.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"com.epam.gym.api"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "  @Regression"
)
public class MainRunnerApi extends AbstractTestNGCucumberTests {
}

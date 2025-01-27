package org.alpercaktug;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.alpercaktug.core.DriverManager;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("(Before Scenario) Execute Scenario Tags: " + scenario.getSourceTagNames());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("(After Scenario)");
        if (scenario.getSourceTagNames().contains("@browser")) {
            System.out.println("(After Scenario) Quit Driver");
            DriverManager.quitDriver();
        }
    }
}

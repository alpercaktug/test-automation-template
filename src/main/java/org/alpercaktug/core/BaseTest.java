package org.alpercaktug.core;

import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    public void setup(String type) {
        driver = DriverManager.getDriver(type);
    }

    public void teardown() {
        DriverManager.quitDriver();
    }
}



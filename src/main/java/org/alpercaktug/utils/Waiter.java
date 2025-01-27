package org.alpercaktug.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Waiter {

    private final WebDriver driver;
    private final int defaultTimeout;

    public Waiter(WebDriver driver, int defaultTimeout) {
        this.driver = driver;
        this.defaultTimeout = defaultTimeout;
    }

    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private <T> T waitForCondition(ExpectedCondition<T> condition, int timeout, String errorMessage) {
        try {
            return getWait(timeout).until(condition);
        } catch (TimeoutException e) {
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void waitForPageLoadComplete() {
        waitForPageLoadComplete(defaultTimeout);
    }

    public void waitForPageLoadComplete(int timeoutInSeconds) {
        waitForCondition(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"),
                timeoutInSeconds,
                "Page did not load within " + timeoutInSeconds + " seconds."
        );
    }

    public void waitForJQueryComplete() {
        waitForJQueryComplete(defaultTimeout);
    }

    public void waitForJQueryComplete(int timeoutInSeconds) {
        waitForCondition(
                driver -> {
                    try {
                        return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
                    } catch (Exception e) {
                        return true; // Assume true if jQuery is not present
                    }
                },
                timeoutInSeconds,
                "jQuery did not finish within " + timeoutInSeconds + " seconds."
        );
    }

    public void click(WebElement element) {
        click(element, defaultTimeout);
    }

    public void click(WebElement element, int timeoutInSeconds) {
        waitForCondition(
                driver -> {
                    try {
                        element.click();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                },
                timeoutInSeconds,
                "Could not click the element within " + timeoutInSeconds + " seconds."
        );
    }

    public void selectFromDropdownByVisibleText(WebElement dropdown, String text) {
        selectFromDropdownByVisibleText(dropdown, text, defaultTimeout);
    }

    public void selectFromDropdownByVisibleText(WebElement dropdown, String text, int timeoutInSeconds) {
        waitForCondition(
                driver -> {
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(text);
                        return select.getFirstSelectedOption().getText().equals(text);
                    } catch (Exception e) {
                        return false;
                    }
                },
                timeoutInSeconds,
                "Could not select option '" + text + "' within " + timeoutInSeconds + " seconds."
        );
    }

    public void selectMultipleFromDropdownByVisibleText(WebElement dropdown, String... texts) {
        waitForCondition(
                driver -> {
                    try {
                        Select select = new Select(dropdown);
                        select.deselectAll();
                        Stream.of(texts).forEach(select::selectByVisibleText);
                        List<String> selectedTexts = select.getAllSelectedOptions()
                                .stream()
                                .map(WebElement::getText)
                                .collect(Collectors.toList());
                        return selectedTexts.containsAll(List.of(texts));
                    } catch (Exception e) {
                        return false;
                    }
                },
                defaultTimeout,
                "Could not select options " + String.join(", ", texts) + " within " + defaultTimeout + " seconds."
        );
    }

    public void clearAndType(WebElement input, String text) {
        clearAndType(input, text, defaultTimeout);
    }

    public void clearAndType(WebElement input, String text, int timeoutInSeconds) {
        waitForCondition(
                driver -> {
                    try {
                        input.clear();
                        input.sendKeys(text);
                        return input.getAttribute("value").equals(text);
                    } catch (Exception e) {
                        return false;
                    }
                },
                timeoutInSeconds,
                "Could not type text '" + text + "' into the input field within " + timeoutInSeconds + " seconds."
        );
    }

    public void clearAndTypeAndTab(WebElement input, String text) {
        clearAndTypeAndTab(input, text, defaultTimeout);
    }

    public void clearAndTypeAndTab(WebElement input, String text, int timeoutInSeconds) {
        waitForCondition(
                driver -> {
                    try {
                        input.clear();
                        input.sendKeys(text);
                        input.sendKeys(Keys.TAB);
                        return input.getAttribute("value").equals(text);
                    } catch (Exception e) {
                        return false;
                    }
                },
                timeoutInSeconds,
                "Could not type text '" + text + "' and tab within " + timeoutInSeconds + " seconds."
        );
    }

    public void deselectAllDropdownOptions(WebElement dropdown) {
        waitForCondition(
                driver -> {
                    try {
                        Select select = new Select(dropdown);
                        select.deselectAll();
                        return select.getAllSelectedOptions().isEmpty();
                    } catch (Exception e) {
                        return false;
                    }
                },
                defaultTimeout,
                "Could not deselect all options from the dropdown."
        );
    }
}

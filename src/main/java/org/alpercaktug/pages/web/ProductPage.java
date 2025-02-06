package org.alpercaktug.pages.web;

import org.alpercaktug.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@data-test-id='price-current-price']")
    List<WebElement> priceElements ;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickFilterByText(String filterName, String filterText) {
        By checkboxLocator = By.xpath("//label[input[@name='" + filterName + "']]//a[normalize-space(text())='" + filterText + "']/ancestor::label/input");
        waitForElementToBeClickable(checkboxLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(checkboxLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(checkboxLocator)).click().perform();

        driver.navigate().refresh();
    }

    public List<WebElement> getPriceElements() {
        waitForElementToBeVisible(priceElements.getFirst());
        return priceElements;
    }

    public WebElement findMaxPriceElement() {
        // Locate all elements with the given XPath
        List<WebElement> priceElements = getPriceElements();

        // Initialize variables for maximum price and its corresponding element
        int maxPrice = Integer.MIN_VALUE;
        WebElement maxPriceElement = null;

        // Loop through all price elements
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText(); // Get the text
            // System.out.println(priceText);
            priceText = priceText.replaceAll("[^\\d]", ""); // Remove non-numeric characters

            if (!priceText.isEmpty()) {
                int price = Integer.parseInt(priceText); // Convert to integer
                if (price > maxPrice) {
                    maxPrice = price; // Update maximum price
                    maxPriceElement = priceElement; // Store the corresponding element
                }
            }
        }

        System.out.println("(Product Page) The maximum price is: " + maxPrice);
        return maxPriceElement;
    }

    public void clickMaxPriceElement(){
        WebElement element = findMaxPriceElement();
        element.click();
    }
}

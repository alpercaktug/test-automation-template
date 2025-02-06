package org.alpercaktug.pages.web;

import org.alpercaktug.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    @FindBy(xpath = "//button[@data-test-id='addToCart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[text()='Sepete git']" )
    WebElement navigateToCartButton;

    @FindBy(xpath = "//h1[@data-test-id='title']" )
    WebElement productTitle;

    @FindBy(xpath = "//div[@data-test-id='default-price']//span")
    WebElement productPrice;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCart(){
        click(addToCartButton);
    }

    public void clickNavigateToCart() {
        click(navigateToCartButton);
    }

    public String getProductTitle(){
        return getText(productTitle);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }
}

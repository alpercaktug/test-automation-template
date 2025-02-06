package org.alpercaktug.pages.web;

import org.alpercaktug.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='product_price_uXU6Q']" )
    WebElement productPriceLabel;

    @FindBy(xpath = "//div[@class='product_name_2Klj3']" )
    WebElement productName;

    @FindBy(xpath = "//span[@class='total_price_3V-CM']" )
    WebElement totalPriceLabel;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPriceText(){
        return getText(productPriceLabel);
    }

    public String getProductNameText(){
        return getText(productName);
    }

    public String getTotalPriceLabel(){
        return getText(totalPriceLabel).trim().replaceAll("\\s+", " ");
    }
}

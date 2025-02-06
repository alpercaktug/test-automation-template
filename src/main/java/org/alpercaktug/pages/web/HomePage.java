package org.alpercaktug.pages.web;

import org.alpercaktug.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']" )
    WebElement acceptCookie;

    Actions actions = new Actions(driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void navigateTo(String url) {
        super.navigateTo(url);
        acceptCookie();
        waitForElementToInvisible(acceptCookie);
    }

    public void acceptCookie() {
        click(acceptCookie);
    }

    public void hoverOverMenuByText(String menuText) {
        By dynamicMenu = By.xpath("//span[contains(@class, 'sf-MenuItems')]/span[text()='" + menuText + "']");
        waitForElementToBeVisible(dynamicMenu);
        actions.moveToElement(getElement(dynamicMenu)).perform();
    }

    public void hoverOverSubMenuByText(String subMenuText) {
        By dynamicSubMenu = By.xpath("//ul[contains(@class, 'sf-ChildMenuItems')]//a[text()='" + subMenuText + "']");
        waitForElementToBeVisible(dynamicSubMenu);
        actions.moveToElement(getElement(dynamicSubMenu)).perform();
    }

    public void clickSubMenuByText(String subMenuText){
        By dynamicSubMenu = By.xpath("//ul[contains(@class, 'sf-ChildMenuItems')]//a[text()='" + subMenuText + "']");
        waitForElementToBeVisible(dynamicSubMenu);
        click(getElement(dynamicSubMenu));
    }
}

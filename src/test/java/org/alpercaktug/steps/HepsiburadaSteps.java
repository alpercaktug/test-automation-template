package org.alpercaktug.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.alpercaktug.core.BaseTest;
import org.alpercaktug.core.DriverManager;
import org.alpercaktug.pages.web.CartPage;
import org.alpercaktug.pages.web.HomePage;
import org.alpercaktug.pages.web.ProductDetailPage;
import org.alpercaktug.pages.web.ProductPage;
import org.alpercaktug.utils.ScenarioContext;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HepsiburadaSteps extends BaseTest {

    private final ScenarioContext scenarioContext;

    WebDriver driver = DriverManager.getDriver("browser");

    HomePage homePage = new HomePage(driver);
    ProductPage productPage = new ProductPage(driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    CartPage cartPage = new CartPage(driver);

    public HepsiburadaSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I visit {string}")
    public void iVisit(String url) {
        homePage.navigateTo(url);
        // Thread.sleep(1000);
    }

    @When("I navigate to {string} > {string} > {string}")
    public void iNavigateTo(String menu, String childMenu, String product) {
        homePage.hoverOverMenuByText(menu);
        homePage.hoverOverSubMenuByText(childMenu);
        homePage.clickSubMenuByText(product);
    }

    @And("I filter by {string} as {string}")
    public void iFilterByAsAndAs(String filterName, String filterText) throws InterruptedException {
        //Thread.sleep(1000);
        productPage.clickFilterByText(filterName, filterText);
    }

    @And("I select the highest priced product from the search results")
    public void iSelectTheHighestPricedProductFromTheSearchResults() {
        productPage.clickMaxPriceElement();
        productPage.handleNewTab();

    }

    @And("I add to cart product on the product detail page")
    public void iAddToCartProductOnTheProductDetailPage() {
        scenarioContext.set("productTitle", productDetailPage.getProductTitle());
        scenarioContext.set("productPrice", productDetailPage.getProductPrice());
        System.out.println("(Product Detail Page) Product Title: " + scenarioContext.get("productTitle"));
        System.out.println("(Product Detail Page) Product Price: " + scenarioContext.get("productPrice"));
        productDetailPage.clickAddToCart();
    }

    @Then("I should see the product in my cart with the same price as the product detail page")
    public void iShouldSeeTheProductInMyCartWithTheSamePriceAsTheProductDetailPage(){
        productDetailPage.clickNavigateToCart();
        System.out.println("(Cart Page) Product Price: " + cartPage.getProductPriceText());
        System.out.println("(Cart Page) Product Title: " + cartPage.getProductNameText());
        System.out.println("(Cart Page) Total Price: " + cartPage.getTotalPriceLabel());
        Assert.assertEquals(scenarioContext.get("productPrice"), cartPage.getTotalPriceLabel());
        Assert.assertEquals(scenarioContext.get("productTitle"), cartPage.getProductNameText());
    }


}

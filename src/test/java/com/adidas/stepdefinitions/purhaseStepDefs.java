package com.adidas.stepdefinitions;


import com.adidas.pages.AdidasPage;
import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.ConfigurationReader;
import com.adidas.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

public class purhaseStepDefs {


    AdidasPage adidasPage = new AdidasPage();
    int expectedPurhaseAMount = 0;

    String orderID;
    int purchaseAmount;

    int listPrice;
    int addingPrice;
    int cartPrice;


    @Given("User is on the Home Page")
    public void user_is_on_the_home_page() {

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }


    @When("User adds {string} from {string}")
    public void user_adds_from(String product, String category) {
        expectedPurhaseAMount+=adidasPage.productAdder(category,product);

    }

    @When("User removes {string} from cart")
    public void user_removes_from_cart(String product) {
        expectedPurhaseAMount-=adidasPage.productRemover(product);

    }


    @When("User places order and captures and logs purchase ID and Amount")
    public void user_places_order_and_captures_and_logs_purchase_id_and_amount() {
        adidasPage.cart.click();
        adidasPage.placeButton.click();

        adidasPage.fillForm();

        adidasPage.purchaseButton.click();


        String confirmation = adidasPage.confirmation.getText();
        System.out.println("confirmation = " + confirmation);

        String[] confirmationArray = confirmation.split("\n");
        orderID = confirmationArray[0];
        System.out.println("orderID = " + orderID);
        purchaseAmount = Integer.parseInt(confirmationArray[1].split(" ")[1]);



    }
    @Then("User verifies purchase amount equals expected")
    public void user_verifies_purchase_amount_equals_expected() {
        int actualAmount = purchaseAmount;
        System.out.println("actualAmount = " + actualAmount);
        System.out.println("expectedOrderAmmount = " + expectedPurhaseAMount);
        Assert.assertEquals(expectedPurhaseAMount,actualAmount);
        BrowserUtils.sleep(1);
        adidasPage.OK.click();
        BrowserUtils.sleep(1);
        Driver.closeDriver();
    }

    @Then("Under {string} category user should see the list of products")
    public void under_category_user_should_see_the_list_of_products(String category, List<String> expectedProducts ) {
        Driver.getDriver().findElement(By.xpath("//a[.='"+category+"']")).click();
        BrowserUtils.sleep(2);

        // we are getting webelements of product from the page and putting their text into a List of String
        List<String> actualProducts = BrowserUtils.getElementsText(adidasPage.products);

        System.out.println("actualProducts = " + actualProducts);
        System.out.println("expectedProducts = " + expectedProducts);
        Assert.assertEquals("verify the list of products failed", expectedProducts,actualProducts);

    }

    @Then("User verifies list and cart price are same and they are equal to {string}")
    public void userVerifiesListAndCartPriceAreSameAndTheyAreEqualTo(String priceString) {
        int expectedPrice = Integer.parseInt(priceString);

        Assert.assertEquals(expectedPrice,listPrice);
        Assert.assertEquals(addingPrice,cartPrice);


    }

    @And("User removes {string} from cart to verify the price")
    public void userRemovesFromCartToVerifyThePrice(String products) {
        cartPrice = adidasPage.productRemover(products);
    }

    @When("User adds {string} from {string} to see the price")
    public void userAddsFromToSeeThePrice(String products, String category) {
        String locator = "//a[.='"+products+"']/../../h5";
        listPrice = Integer.parseInt(Driver.getDriver().findElement(By.xpath(locator)).getText().substring(1));
        addingPrice = adidasPage.productAdder(category,products);
    }

}
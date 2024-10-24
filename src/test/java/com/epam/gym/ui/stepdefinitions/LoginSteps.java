package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.LoginPage;
import com.epam.gym.pages.RegistrationPage;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginSteps {
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    @Given("I am on the Login page")
    public void iAmOnTheLoginPage(){
        System.out.println("I am on the Login page");
        String uri = ConfigLoader.getProperty("browser.uri");
        WebDriver driver = DriverSingleton.getDriver();
      //  loginPage = new LoginPage(driver);
        SeleniumCommon.openBrowser(uri);
        registrationPage = new RegistrationPage(driver);
        loginPage = registrationPage.navigateToLoginPage();
        System.out.println(driver.getCurrentUrl());
    }

    @When("I enter {string} and {string}")
    public void iEnterEmailAndPassword(String email, String password){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton(){
        loginPage.submit();
    }

    @Then("I should see the {string} in the {string}")
    public void iShouldSeeTheMessage(String message, String locator) throws InterruptedException{
        Thread.sleep(2000);
        if (locator.toLowerCase().equals("toast")){
            String  toastElement = loginPage.getToastMessage();
            System.out.println(toastElement);

        }
        else if(locator.toLowerCase().equals("PassInline")){
            String  passwordElement = loginPage.getPassErrorMessage();
            Assert.assertEquals(passwordElement, message);
        }
        else if(locator.toLowerCase().equals("EmailInline")){
            String  passwordElement = loginPage.getEmailErrorMessage();
            Assert.assertEquals(passwordElement, message);
        }

    }


    @Then("I should see the Create New Account link")
    public void iShouldSeeTheCreateNewAccountLink() {
        boolean isLinkPresent = loginPage.isCreateAccountLinkPresent();
        Assert.assertTrue(isLinkPresent, "The Create New Account link is not displayed.");
        loginPage.validateThePresenceOfRegistrationButton();
    }
}

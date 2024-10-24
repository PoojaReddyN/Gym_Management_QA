package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.browsers.SeleniumCommon;
import com.epam.gym.pages.*;
import com.epam.gym.utils.ConfigLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CoachProfileSteps {
    String uri;
    WebDriver driver;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    UpdatePage updatePage;
    CoachProfilePage coachProfilePage;
    CoachNavigation workoutsPage;
    CoachesPage coachesPage;

    @Given("I log in to the application")
    public void iLogInToTheApplication() {
        String coachEmail = ConfigLoader.getProperty("coachEmail");
        String coachPassword = ConfigLoader.getProperty("coachPassword");
        System.out.println(coachEmail + " " + coachPassword);
        uri = ConfigLoader.getProperty("browser.uri");
        driver = SeleniumCommon.openBrowser(uri);
        registrationPage = new RegistrationPage(driver);
        loginPage = registrationPage.navigateToLoginPage();
        loginPage.enterEmail(coachEmail);
        loginPage.enterPassword(coachPassword);
        loginPage.submit();

    }

    @And("I am on the coach profile update page")
    public void iAmOnTheCoachProfileUpdatePage() throws InterruptedException {
        updatePage = new UpdatePage(driver);
        Thread.sleep(3000);
        updatePage.clickProfileIcon();
        Thread.sleep(3000);
    }

    @When("I enter {string} in the name field")
    public void iEnterInTheNameField(String name) {
        coachProfilePage = new CoachProfilePage(driver);
        System.out.println(driver.getCurrentUrl());
        coachProfilePage.enterName(name);
    }

    @And("I enter {string} in the title field")
    public void iEnterInTheTitleField(String title) {
        coachProfilePage.enterTitle(title);
    }

    @And("I enter {string} in the about field")
    public void iEnterInTheAboutField(String about) throws InterruptedException {
        coachProfilePage.enterAbout(about);
        Thread.sleep(3000);
    }

    @And("I enter {string} in the specialization field")
    public void iEnterInTheSpecializationField(String specialization) {
        coachProfilePage.enterSpecialization(specialization);
    }

    @And("I upload a certificate file named {string}")
    public void iUploadACertificateFileNamed(String arg0) {
    }

    @And("I click on save changes button there")
    public void iClickOnSaveChangesButtonThere() {
        coachProfilePage.clickSaveChangesButton();
    }

    @Then("I should see a success message {string}")
    public void iShouldSeeASuccessMessage(String expectedMessage) {
        Assert.assertTrue(coachProfilePage.getSavedSuccessfullyToastMessage().contains(expectedMessage),
                "Response does not contain the expected message: "+expectedMessage);
    }

    @Then("I should see an error message for the empty field at {string} as {string}")
    public void iShouldSeeAnErrorMessageForTheEmptyFieldAt(String errorLocation, String expectedErrorMessage) throws InterruptedException{
        if(errorLocation.equalsIgnoreCase("name")){
            Thread.sleep(2000);
            Assert.assertTrue(coachProfilePage.getNameErrorMessage().contains(expectedErrorMessage),
                    "Response does not contain the expected message: "+expectedErrorMessage);
        }
        else if(errorLocation.equalsIgnoreCase("title")) {
            Thread.sleep(2000);
            Assert.assertTrue(coachProfilePage.getTitleErrorMessage().contains(expectedErrorMessage),
                    "Response does not contain the expected message: " + expectedErrorMessage);
        }
        else if(errorLocation.equalsIgnoreCase("about")){
            Thread.sleep(2000);
            Assert.assertTrue(coachProfilePage.getAboutErrorMessage().contains(expectedErrorMessage),
                    "Response does not contain the expected message: "+expectedErrorMessage);
        }
    }

}

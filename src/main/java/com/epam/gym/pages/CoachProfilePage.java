package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoachProfilePage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name = 'name']")
    WebElement yourName;

    @FindBy(xpath = "//input[@name = 'title']")
    WebElement yourTitle;

    @FindBy(xpath = "//textarea[@name = 'about']")
    WebElement yourAbout;

    @FindBy(xpath = "//input[@name = 'specializations']")
    WebElement yourSpecialization;

    @FindBy(xpath = "//button[text()='Select File']")
    WebElement selectFileButton;

    @FindBy(xpath = "//button[text()='Save Changes']")
    WebElement saveChangesButton;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private WebElement toastElement;

    @FindBy(id = ":r10:-helper-text")
    WebElement nameError;

    @FindBy(id = ":r11:-helper-text")
    WebElement titleError;

    @FindBy(id = ":r12:-helper-text")
    WebElement aboutError;


    public CoachProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name){
        yourName.clear();
        yourName.sendKeys(name);
    }

    public void enterTitle(String title){
        yourTitle.clear();
        yourTitle.sendKeys(title);
    }

    public void enterAbout(String about){
        yourAbout.clear();
        yourAbout.sendKeys(about);
    }

    public void enterSpecialization(String specialization){
        yourAbout.clear();
        yourSpecialization.sendKeys(specialization);
    }

    public void addCertificate(){

    }

    public String getNameErrorMessage(){
        return nameError.getText();
    }

    public String getTitleErrorMessage(){
        return titleError.getText();
    }

    public String getAboutErrorMessage(){
        return aboutError.getText();
    }

    public void clickSaveChangesButton(){
        saveChangesButton.click();
    }

    public String getSavedSuccessfullyToastMessage(){
        WaitHelper.waitForElementToBeVisible(driver,toastElement,10);
        return toastElement.getText();
    }

}

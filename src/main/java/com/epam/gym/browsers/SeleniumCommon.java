package com.epam.gym.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumCommon {
    private static WebDriver driver;

    public static WebDriver openBrowser(String uri){
        driver = DriverSingleton.getDriver();
        driver.get(uri);
        driver.manage().window().maximize();
        return driver;
    }


}

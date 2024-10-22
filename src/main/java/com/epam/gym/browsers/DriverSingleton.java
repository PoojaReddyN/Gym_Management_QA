package com.epam.gym.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {

    private static WebDriver driver=null;

    private DriverSingleton() {
    }


    public static WebDriver getDriver() {
        if (driver == null) {
            FirefoxOptions f=new FirefoxOptions();
           // f.addArguments("--headless");
            driver=new FirefoxDriver(f);
                }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void jsExecutorExample(){
        FirefoxOptions f=new FirefoxOptions();
        f.addArguments("--headless");
        driver=new FirefoxDriver(f);
        driver.get("https://demoqa.com/automation-practice-form");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        String str= (String)js.executeScript("return document.title");
        System.out.println(str);
    }
}

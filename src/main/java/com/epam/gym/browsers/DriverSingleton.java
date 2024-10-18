package com.epam.gym.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSingleton {

    private static WebDriver driver=null;

    private DriverSingleton() {
    }

    public static void main(String[] args) {
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }
    public static WebDriver getDriver() {
        if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}

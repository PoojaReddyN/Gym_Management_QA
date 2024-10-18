package com.epam.gym.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public enum BrowserType {
        CHROME,
        EDGE,
        FIREFOX
    }

    public static WebDriver getDriver() {
        BrowserType browserType = getBrowserTypeFromConfig();
        boolean headless = isHeadlessFromConfig();
        System.out.println(headless);
        return createDriver(browserType,headless);
    }

    private static BrowserType getBrowserTypeFromConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            String browserTypeString = properties.getProperty("browser.type", "firefox"); // Default to firefox if property is not found
            return parseBrowserType(browserTypeString);
        } catch (IOException e) {
            logger.error("Error loading config.properties: {}", e.getMessage());
        }

        return BrowserType.CHROME;
    }

    private static boolean isHeadlessFromConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            return Boolean.parseBoolean(properties.getProperty("headless", "false"));
        } catch (IOException e) {
            logger.error("Error loading config.properties: {}", e.getMessage());
        }
        return false; // Default to false if property not found
    }

    private static BrowserType parseBrowserType(String browserTypeString) {
        switch (browserTypeString.toLowerCase()) {
            case "chrome":
                return BrowserType.CHROME;
            case "edge":
                return BrowserType.EDGE;
            case "firefox":
                return BrowserType.FIREFOX;
            default:
                throw new IllegalArgumentException("Browser \"" + browserTypeString + "\" not supported.");
        }
    }


    private static WebDriver createDriver(BrowserType browserType, boolean headless) {
        MutableCapabilities options = getBrowserOptions(browserType, headless);
        switch (browserType) {
            case CHROME:
                return new ChromeDriver((ChromeOptions) options);
            case EDGE:
                return new EdgeDriver((EdgeOptions) options);
            case FIREFOX:
                return new FirefoxDriver((FirefoxOptions) options);
            default:
                throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        }
    }

    private static MutableCapabilities getBrowserOptions(BrowserType browserType, boolean headless) {
        MutableCapabilities options;
        switch (browserType) {
            case CHROME:
                options = new ChromeOptions();
                break;
            case EDGE:
                options = new EdgeOptions();
                break;
            case FIREFOX:
                options = new FirefoxOptions();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        }

        if (headless) {
            ((ChromeOptions) options).addArguments("--headless");
        }

        return options;
    }
}

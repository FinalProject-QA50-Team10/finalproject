package com.telerikacademy.testframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class Driver implements WebDriver {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public Driver(WebDriver driver) {
        webDriver = driver;
        Duration timeout = Duration.ofSeconds(Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds")));
        webDriverWait = new WebDriverWait(driver, timeout);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    @Override
    public void get(String s) {
        LOGGER.info("Navigating to page: " + s);
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = webDriver.getCurrentUrl();
        LOGGER.info("Getting current URL: " + currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String currentTitle = webDriver.getTitle();
        LOGGER.info("Getting Title: " + currentTitle);
        return currentTitle;
    }

    @Override
    public List<WebElement> findElements(By by) {
        LOGGER.info("Finding element with locator: " + by.toString());
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        LOGGER.info("Finding all elements with locator: " + by.toString());
        WebElement elementFound = webDriver.findElement(by);
        return webDriverWait.until(ExpectedConditions.visibilityOf(elementFound));
    }

    @Override
    public String getPageSource() {
        LOGGER.info("Getting page source...");
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        LOGGER.info("Closing Browser...");
        webDriver.close();
    }

    @Override
    public void quit() {
        LOGGER.info("Quiting driver...");
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        LOGGER.info("Getting Window handles...");
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }
}

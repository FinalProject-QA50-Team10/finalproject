package com.telerikacademy.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        getWebDriver().get(getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void pressKey(Keys key) {
        // TODO: Implement the method
        // 1. Initialize Actions
        Actions action = new Actions(driver);
        // 2. Perform key press
        action.sendKeys(key).build().perform();
    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public void dragAndDropElement(String fromElementLocator, String toElementLocator) {

        String fromLocator = getLocatorValueByKey(fromElementLocator);
        WebElement fromElement = driver.findElement(By.xpath(fromLocator));

        String toLocator = getLocatorValueByKey(toElementLocator);
        WebElement toElement = driver.findElement(By.xpath(toLocator));

        Actions actions = new Actions(driver);

        Action dragAndDrop = actions.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();
        dragAndDrop.perform();
    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Initialize Wait utility with default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Use the method that checks for Element present
        // 3. Fail the test with meaningful error message in case the element is not present
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    //###################### Asserts ##################
    public void assertElementPresent(String locator) {
        Assertions.assertNotNull(driver.findElement(By.xpath(getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
        System.out.println("Elements are present.");
    }

    public void assertElementNotPresent(String locator) {
        try {
            driver.findElement(By.xpath(getUIMappingByKey(locator)));
            Assertions.fail(format("Element with %s is present but should not be.", locator));
        } catch (NoSuchElementException e) {
            System.out.println("Element is not present.");
        }
    }

    public void assertElementTextEquals(String locator, String expectedText) {
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(expectedText, actualText, "Text does not match for element: " + locator);
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));
        // 2. Get the element attribute
        String value = element.getAttribute(attributeName);
        // 3. Assert equality with expected value
        Assertions.assertEquals(format("Element with locator %s doesn't match", attributeName), getLocatorValueByKey(attributeValue), value);
    }


    public void assertUsersListIsNotEmpty(String searchResultsPath) {
        var users = getElements(searchResultsPath);
        Assertions.assertFalse(users.isEmpty());
        System.out.println("Users list in not empty.");
    }

    public void assertUrlsAreEquals(String expectedUrl, String actualUrl) {
        Assertions.assertEquals(expectedUrl, actualUrl, "Expected URL is different than actual.");
        System.out.println("URLs are equal.");
    }

    public void assertUsersAttribute(String name, String path) {
        var users = getElements(path);
        for (var user : users) {
            String result = user.getText();
            if (!result.contains(name)) {
                throw new IllegalArgumentException(format("Expected attribute is different than actual. " +
                        "Actual is %s, expected is %s.", result, name));
            }
        }
        System.out.println("Attributes are correct.");
    }


    public void assertSearchedUsersContainsName(String name, String path) {
        var users = getElements(path);
        for (var user : users) {
            String result = user.getText();
            if (!result.contains(name)) {
                throw new IllegalArgumentException(format("Expected name is different than actual. " +
                        "Actual is %s, expected is %s.", result, name));
            }
        }
        System.out.println("Name is correct.");
    }

    public WebElement getElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        LOGGER.info("Hovering on element " + key);
        return driver.findElement(By.xpath(locator));
    }

    public List<WebElement> getElements(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        LOGGER.info("Hovering on element " + key);
        return driver.findElements(By.xpath(locator));
    }

    public String getElementAttribute(String locator, String attributeName) { // attributeName = po id, x-path..
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String locatorKey = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(locatorKey));
        // 2. Get the element attribute
        // 3. Return the expected value
        return element.getAttribute(attributeName);
    }

    public void scrollDown(String locator) {
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));

        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
        //  actions.moveToElement(element);
        actions.perform();
    }

    public String generateLongString() {
        StringBuilder longString = new StringBuilder();
        String charactersToAdd = "BCDERYAWGzbnml";
        for (int i = 0; i < 1001; i++) {
            longString.append(charactersToAdd);
        }
        return longString.toString();
    }


    public String generateRandomText(int minLength, int maxLength) {
        int length = generateRandomNumber(minLength, maxLength);

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder randomText = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            char randomChar = alphabet[random.nextInt(alphabet.length)];
            randomText.append(randomChar);
        }
        return randomText.toString();
    }

    public static String generateRandomTextExactLength(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));

        }
        return builder.toString();
    }

    private int generateRandomNumber(int min, int max) {
        Random number = new Random();
        return number.nextInt(max - min) + min;
    }

    private String getLocatorValueByKey(String locator) {
        return format(getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
    }


}

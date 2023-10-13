package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegisteredUserPage extends BasePage {
    public RegisteredUserPage(WebDriver driver) {
        super(driver, REGISTER_PAGE);
    }

    public void assertUpdateProfileButtonIsVisible() {
        actions.waitForElementVisible(UPDATE_PROFILE_BUTTON_PATH);
    }

    public void assertWelcomeMessage() {
        actions.assertElementPresent(WELCOME_MESSAGE_PATH);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(Utils.getConfigPropertyByKey(REGISTER_PAGE), currentUrl,
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + REGISTER_PAGE);
    }
}


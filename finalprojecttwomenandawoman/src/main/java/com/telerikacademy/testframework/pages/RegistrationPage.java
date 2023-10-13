package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver, REGISTER_PAGE);
    }

    public void fillRegisterForm(String username, String email, String password){
        actions.waitForElementClickable(USERNAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), USERNAME_FIELD_PATH);
        actions.waitForElementClickable(EMAIL_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(email), EMAIL_FIELD_PATH);
        actions.waitForElementClickable(PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(CONFIRM_PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), CONFIRM_PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(REGISTRATION_BUTTON_PATH);
        actions.clickElement(REGISTRATION_BUTTON_PATH);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(Utils.getConfigPropertyByKey(REGISTER_PAGE), currentUrl,
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + REGISTER_PAGE);
    }

    public void assertErrorMessageIsVisible() {
        actions.waitForElementVisible(REGISTRATION_ERROR_MESSAGE_PATH);
        actions.assertElementPresent(REGISTRATION_ERROR_MESSAGE_PATH);
    }

    public void assertErrorMessage(String expectedMessage) {
        var actualMessage = actions.getElement(REGISTRATION_ERROR_MESSAGE_PATH).getText();
        Assertions.assertEquals(expectedMessage, actualMessage, String.format("Expected message is different than actual. " +
                "Expected: %s. Actual: %s", expectedMessage, actualMessage));
    }
}

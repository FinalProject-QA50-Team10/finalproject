package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }

    public void login(String username, String password){
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), LOGIN_USERNAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), LOGIN_PASSWORD_FIELD_PATH);
        actions.clickElement(LOGIN_BUTTON_PATH);
    }

    public void navigateToHomePage(){
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(Utils.getConfigPropertyByKey(LOGIN_PAGE)),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + LOGIN_PAGE);
    }

    public void assertLoginPageTitle(){
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
    }

    public void assertPasswordFieldVisible(){
        actions.assertElementPresent(LOGIN_PASSWORD_FIELD_PATH);
    }

    public void assertUsernameFieldVisible(){
        actions.assertElementPresent(LOGIN_USERNAME_FIELD_PATH);
    }

    public void assertLoginButtonVisible(){
        actions.assertElementPresent(LOGIN_BUTTON_PATH);
    }

    public void assertLoginErrorMessage() {
        actions.assertElementPresent(LOGIN_ERROR_MESSAGE_PATH);
    }

    public void assertLogoutErrorMessage() {
        actions.assertElementPresent(LOGOUT_ERROR_MESSAGE_PATH);
    }
}

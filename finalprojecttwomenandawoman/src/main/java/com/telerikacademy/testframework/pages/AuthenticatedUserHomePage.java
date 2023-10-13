package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AuthenticatedUserHomePage extends BasePage {
    public AuthenticatedUserHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void logout(){
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
        actions.waitForElementClickable(LOG_OUT_BUTTON_PATH);
        actions.clickElement(LOG_OUT_BUTTON_PATH);
    }

    public void navigateToHomePage(){
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    public void assertLogoutButtonIsVisible() {
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
    }

    public void assertPersonalProfileButtonIsVisible() {
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
    }

    public void assertAddNewPostButtonIsVisible() {
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
    }
}

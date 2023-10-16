package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AuthenticatedUserHomePage extends BasePage {
    public AuthenticatedUserHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void logout() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
        actions.waitForElementClickable(LOGOUT_BUTTON_PATH);
        actions.clickElement(LOGOUT_BUTTON_PATH);
    }

    public void navigateToHomePage() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    public void clickPersonalProfileButton() {
        actions.waitForElementClickable(PERSONAL_PROFILE_BUTTON_PATH);
        actions.clickElement(PERSONAL_PROFILE_BUTTON_PATH);
    }

    public void fillSearchForm(String jobTitle, String names) {
        actions.waitForElementClickable(SEARCH_JOB_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(jobTitle), SEARCH_JOB_FIELD_PATH);
        actions.waitForElementClickable(SEARCH_NAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(names), SEARCH_NAME_FIELD_PATH);
        actions.waitForElementClickable(SEARCH_BUTTON_PATH);
        actions.clickElement(SEARCH_BUTTON_PATH);
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

package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AdminHomePage extends BasePage {
    public AdminHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void deletePostWithAdminAccess() {
        navigateToLatestPost();
        navigateToExplorePost();
        actions.waitForElementClickable(DELETE_POST_BUTTON);
        actions.clickElement(DELETE_POST_BUTTON);
        actions.waitForElementClickable(DELETE_DROP_DOWN_MENU);
        actions.clickElement(DELETE_BUTTON_SELECT);
        actions.waitForElementClickable(DELETE_BUTTON_SUBMIT);
        actions.clickElement(DELETE_BUTTON_SUBMIT);
    }

    public void disableUserWithAdminAccess() {
        navigateToAdminZone();
        actions.waitForElementClickable(DISABLE_USER_BUTTON);
        actions.clickElement(DISABLE_USER_BUTTON);
    }

    public void enableUserWithAdminAccess() {
        navigateToAdminZone();
        actions.waitForElementVisible(ENABLE_USER_BUTTON);
        actions.clickElement(ENABLE_USER_BUTTON);
    }

    public void editPostWithAdminAccess() {
        navigateToLatestPost();
        navigateToExplorePost();
        actions.waitForElementClickable(EDIT_POST_BUTTON);
        actions.clickElement(EDIT_POST_BUTTON);
        actions.waitForElementClickable(POSTS_VISIBILITY);
        actions.clickElement(POSTS_VISIBILITY);
        actions.waitForElementClickable(POSTS_PUBLIC_VISIBILITY);
        actions.clickElement(POSTS_PUBLIC_VISIBILITY);
        actions.waitForElementClickable(POST_MESSAGE_FIELD);
        actions.typeValueInField(POST_EDIT_MESSAGE, POST_MESSAGE_FIELD);
        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void viewUsersAsAdmin() {
        actions.waitForElementClickable(ADMIN_ZONE_BUTTON);
        actions.clickElement(ADMIN_ZONE_BUTTON);
        actions.waitForElementClickable(VIEW_USERS_BUTTON);
        actions.clickElement(VIEW_USERS_BUTTON);
    }

    public void assertAdminCanViewAllUsers() {
        actions.waitForElementClickable(USER_NAMES_AND_SEE_USER_PROFILE);
        actions.assertElementPresent(USER_NAMES_AND_SEE_USER_PROFILE);
    }

    public void assertEnableUserButton() {
        actions.waitForElementPresent(ENABLE_USER_BUTTON);
        actions.assertElementPresent(ENABLE_USER_BUTTON);
    }

    public void assertDisableUserButton() {
        actions.waitForElementPresent(DISABLE_USER_BUTTON);
        actions.assertElementPresent(DISABLE_USER_BUTTON);
    }

    private void navigateToLatestPost() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);
    }

    private void navigateToExplorePost() {
        actions.waitForElementClickable(BROWSE_POST_SELECTION_BUTTON);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
    }

    private void navigateToAdminZone() {
        actions.waitForElementClickable(ADMIN_ZONE_BUTTON);
        actions.clickElement(ADMIN_ZONE_BUTTON);
        actions.waitForElementClickable(VIEW_USERS_BUTTON);
        actions.clickElement(VIEW_USERS_BUTTON);
        actions.waitForElementClickable(SEE_USER_PROFILE_BUTTON);
        actions.clickElement(SEE_USER_PROFILE_BUTTON);
    }
}

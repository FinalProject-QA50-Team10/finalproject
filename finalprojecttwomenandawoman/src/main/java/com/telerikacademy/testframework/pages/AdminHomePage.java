package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.DISABLE_USER_BUTTON;

public class AdminHomePage extends BasePage{
    public AdminHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void deletePostWithAdminAccess() {

    }

    public void disableUserWithAdminAccess() {
        actions.waitForElementClickable(ADMIN_ZONE_BUTTON);
        actions.clickElement(ADMIN_ZONE_BUTTON);
        actions.waitForElementClickable(VIEW_USERS_BUTTON);
        actions.clickElement(VIEW_USERS_BUTTON);
        actions.waitForElementClickable(SEE_USER_PROFILE_BUTTON);
        actions.clickElement(SEE_USER_PROFILE_BUTTON);
        actions.waitForElementClickable(DISABLE_USER_BUTTON);
        actions.clickElement(DISABLE_USER_BUTTON);
    }

    public void enableUserWithAdminAccess() {
        actions.waitForElementClickable(ADMIN_ZONE_BUTTON);
        actions.clickElement(ADMIN_ZONE_BUTTON);
        actions.waitForElementClickable(VIEW_USERS_BUTTON);
        actions.clickElement(VIEW_USERS_BUTTON);
        actions.waitForElementClickable(SEE_USER_PROFILE_BUTTON);
        actions.clickElement(SEE_USER_PROFILE_BUTTON);
        actions.waitForElementVisible(ENABLE_USER_BUTTON);
        actions.clickElement(ENABLE_USER_BUTTON);
    }

    public void editPostAsAdmin() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);
        actions.waitForElementClickable(BROWSE_POST_SELECTION_BUTTON);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
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


}

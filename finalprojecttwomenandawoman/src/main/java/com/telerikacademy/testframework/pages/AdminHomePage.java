package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.DISABLE_USER_BUTTON;

public class AdminHomePage extends BasePage{
    public AdminHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void validateAdminCanDisableUser() {
        actions.waitForElementClickable(ADMIN_ZONE_BUTTON);
        actions.clickElement(ADMIN_ZONE_BUTTON);
        actions.waitForElementClickable(VIEW_USERS_BUTTON);
        actions.clickElement(VIEW_USERS_BUTTON);
        actions.waitForElementClickable(SEE_USER_PROFILE_BUTTON);
        actions.clickElement(SEE_USER_PROFILE_BUTTON);
        actions.waitForElementClickable(DISABLE_USER_BUTTON);
        actions.clickElement(DISABLE_USER_BUTTON);
    }

}

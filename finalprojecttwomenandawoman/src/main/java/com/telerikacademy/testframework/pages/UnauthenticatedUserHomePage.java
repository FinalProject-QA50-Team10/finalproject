package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class UnauthenticatedUserHomePage extends BasePage{
    public UnauthenticatedUserHomePage(WebDriver driver){
        super(driver, HOME_PAGE);
    }

    public void clickSignInButton()
    {
        actions.waitForElementClickable(SIGN_IN_BUTTON);
        actions.clickElement(SIGN_IN_BUTTON);
    }
    public void assertSearchFormVisible() {
        actions.assertElementPresent(SEARCH_FORM);
    }


}

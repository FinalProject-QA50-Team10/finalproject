package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class UnauthenticatedUserHomePage extends BasePage{
    public UnauthenticatedUserHomePage(WebDriver driver){
        super(driver, HOME_PAGE);
    }

    public void clickSignInButton()
    {
        actions.waitForElementClickable(SIGN_IN_BUTTON_PATH);
        actions.clickElement(SIGN_IN_BUTTON_PATH);
    }

    public void clickRegisterButton()
    {
        actions.waitForElementClickable(REGISTER_BUTTON_PATH);
        actions.clickElement(REGISTER_BUTTON_PATH);
    }
    public void searchForm(String jobTitle, String names){
        actions.waitForElementClickable(SEARCH_JOB_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(jobTitle), SEARCH_JOB_FIELD_PATH);
        actions.waitForElementClickable(SEARCH_NAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(names), SEARCH_NAME_FIELD_PATH);
        actions.waitForElementClickable(SEARCH_BUTTON_PATH);
        actions.clickElement(SEARCH_BUTTON_PATH);
    }
}

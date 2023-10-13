package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class SearchedUsersPage extends BasePage {

    public SearchedUsersPage(WebDriver driver) {
        super(driver, SEARCHED_USERS_PAGE);
    }

    public void assertSearchResultsAreVisible() {
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
    }

    public void assertUsersAreVisible() {
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
    }

    public void assertUsersAttributes(String attribute, String path) {
        actions.assertUsersAttribute(attribute, path);
    }
    public void navigateToHomePage(){
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }
}

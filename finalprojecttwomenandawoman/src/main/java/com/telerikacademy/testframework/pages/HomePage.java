package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver, "WEare.homePage");
    }
    public void assertSearchFormVisible() {
        actions.assertElementPresent("homePage.searchForm");
    }

}

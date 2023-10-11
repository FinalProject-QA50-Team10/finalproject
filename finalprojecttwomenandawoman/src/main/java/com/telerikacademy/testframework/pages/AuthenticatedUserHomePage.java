package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AuthenticatedUserHomePage extends BasePage{
    public AuthenticatedUserHomePage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

}

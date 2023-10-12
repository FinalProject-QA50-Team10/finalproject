package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class PostPage extends BasePage{
    public PostPage(WebDriver driver, String urlKey) {
        super(driver, HOME_PAGE);
    }


}

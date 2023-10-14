package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.LATEST_POST_BUTTON;
import static com.telerikacademy.testframework.pages.Constants.POSTS_URL;

public class FeedPage extends BasePage {
    public FeedPage(WebDriver driver) {
        super(driver, POSTS_URL);
    }

    public void navigateToPublicFeed() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);
    }
}

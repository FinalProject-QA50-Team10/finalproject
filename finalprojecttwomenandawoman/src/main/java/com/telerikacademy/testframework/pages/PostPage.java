package com.telerikacademy.testframework.pages;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.ADMIN_ZONE_BUTTON;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void createPublicPost() {
        actions.waitForElementClickable(POSTS_ADD_NEW_POST);
        actions.clickElement(POSTS_ADD_NEW_POST);
        actions.waitForElementClickable(POSTS_VISIBILITY);
        actions.clickElement(POSTS_VISIBILITY);
        actions.waitForElementClickable(POSTS_PUBLIC_VISIBILITY);
        actions.clickElement(POSTS_PUBLIC_VISIBILITY);
        actions.waitForElementClickable(POST_MESSAGE_FIELD);
        actions.typeValueInField(POST_MESSAGE, POST_MESSAGE_FIELD);
        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void createPrivatePost() {

    }

}

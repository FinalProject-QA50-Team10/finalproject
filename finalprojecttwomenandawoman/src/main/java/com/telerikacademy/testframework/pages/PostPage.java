package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void createPost(String postType) {
        actions.waitForElementClickable(POSTS_ADD_NEW_POST);
        actions.clickElement(POSTS_ADD_NEW_POST);
        actions.waitForElementClickable(POSTS_VISIBILITY);
        actions.clickElement(POSTS_VISIBILITY);

        if (postType.equalsIgnoreCase("public")) {
            actions.waitForElementClickable(POSTS_PUBLIC_VISIBILITY);
            actions.clickElement(POSTS_PUBLIC_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(POST_MESSAGE, POST_MESSAGE_FIELD);
        } else if (postType.equalsIgnoreCase("private")) {
            actions.waitForElementClickable(POSTS_PRIVATE_VISIBILITY);
            actions.clickElement(POSTS_PRIVATE_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(POST_MESSAGE_PRIVATE, POST_MESSAGE_FIELD);
        }

        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void deletePost() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
        actions.waitForElementClickable(DELETE_POST_BUTTON);
        actions.clickElement(DELETE_POST_BUTTON);
        actions.waitForElementClickable(DELETE_DROP_DOWN_MENU);
        actions.clickElement(DELETE_DROP_DOWN_MENU);
        actions.waitForElementClickable(DELETE_BUTTON_SELECT);
        actions.clickElement(DELETE_BUTTON_SELECT);
        actions.waitForElementClickable(DELETE_BUTTON_SUBMIT);
        actions.clickElement(DELETE_BUTTON_SUBMIT);
    }
}

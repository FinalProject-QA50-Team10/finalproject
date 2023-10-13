package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class CommentPage extends BasePage {
    public CommentPage(WebDriver driver) {
        super(driver, POSTS_URL);
    }

    public void createComment(String commentText) {
        //navigate to latest post page
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);

        //browse all public posts
        actions.waitForElementVisible(POSTS_VISIBILITY);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);

        //navigate to latest post - explore post
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);

        //comment field text
        actions.waitForElementClickable(COMMENT_TEXT_FIELD);
        actions.typeValueInField(commentText, COMMENT_TEXT_FIELD);

        //submit button
        actions.waitForElementClickable(COMMENT_SUBMIT_BUTTON);
        actions.clickElement(COMMENT_SUBMIT_BUTTON);
    }

}

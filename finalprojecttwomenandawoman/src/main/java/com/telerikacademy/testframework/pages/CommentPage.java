package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
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

    public void editComment(String newCommentText) {

        //navigate to latest posts
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);

        //browse all public posts
        actions.waitForElementVisible(POSTS_VISIBILITY);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);

        //explore post
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);

        //click show comments on the post
        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        //click edit comment
        actions.waitForElementClickable(EDIT_COMMENT_BUTTON);
        actions.clickElement(EDIT_COMMENT_BUTTON);

        //enter text
        actions.waitForElementClickable(COMMENT_TEXT_FIELD);
        actions.typeValueInField(newCommentText, COMMENT_TEXT_FIELD);

        //click edit comment button
        actions.waitForElementClickable(EDIT_COMMENT_SUBMIT_BUTTON);
        actions.clickElement(EDIT_COMMENT_SUBMIT_BUTTON);

        //click show comments on the post
//        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
//        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        //assert text
    }

    public void deleteComment() {
        // Navigate to latest posts
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);

        // Browse all public posts
        actions.waitForElementVisible(POSTS_VISIBILITY);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);

        // Explore post
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);

        // Click show comments on the post
        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        // Click delete comment
        actions.waitForElementClickable(DELETE_COMMENT_BUTTON);
        actions.clickElement(DELETE_COMMENT_BUTTON);

        // Confirm delete
        actions.waitForElementClickable(DELETE_DROPDOWN_MENU);
        actions.clickElement(DELETE_DROPDOWN_MENU);
        actions.waitForElementClickable(DELETE_DROPDOWN_SELECT);
        actions.clickElement(DELETE_DROPDOWN_SELECT);
        actions.waitForElementClickable(DELETE_BUTTON_SUBMIT);
        actions.clickElement(DELETE_BUTTON_SUBMIT);
    }

        public void likeComment() {
        }

        public void dislikeComment() {

        }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(Utils.getConfigPropertyByKey(POSTS_URL), currentUrl,
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + LOGIN_PAGE);
    }

}


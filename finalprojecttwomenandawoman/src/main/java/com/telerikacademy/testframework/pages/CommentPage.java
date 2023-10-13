package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class CommentPage extends BasePage {
    public CommentPage(WebDriver driver) {
        super(driver, POSTS_URL);
    }

    private void navigateToLatestPublicPost() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);

        actions.waitForElementVisible(POSTS_VISIBILITY);
        actions.clickElement(BROWSE_POST_SELECTION_BUTTON);

        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
    }

    public void createComment(String commentText) {
        navigateToLatestPublicPost();

        actions.waitForElementClickable(COMMENT_TEXT_FIELD);
        actions.typeValueInField(commentText, COMMENT_TEXT_FIELD);

        actions.waitForElementClickable(COMMENT_SUBMIT_BUTTON);
        actions.clickElement(COMMENT_SUBMIT_BUTTON);
    }

    public void editComment(String newCommentText) {
        navigateToLatestPublicPost();

        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        actions.waitForElementClickable(EDIT_COMMENT_BUTTON);
        actions.clickElement(EDIT_COMMENT_BUTTON);

        actions.waitForElementClickable(COMMENT_TEXT_FIELD);
        actions.typeValueInField(newCommentText, COMMENT_TEXT_FIELD);

        actions.waitForElementClickable(EDIT_COMMENT_SUBMIT_BUTTON);
        actions.clickElement(EDIT_COMMENT_SUBMIT_BUTTON);

        //click show comments on the post
        //actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        //actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);
        //assert text
    }

    public void deleteComment() {
        navigateToLatestPublicPost();

        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        actions.waitForElementClickable(DELETE_COMMENT_BUTTON);
        actions.clickElement(DELETE_COMMENT_BUTTON);

        actions.waitForElementClickable(DELETE_DROPDOWN_MENU);
        actions.clickElement(DELETE_DROPDOWN_MENU);
        actions.waitForElementClickable(DELETE_DROPDOWN_SELECT);
        actions.clickElement(DELETE_DROPDOWN_SELECT);
        actions.waitForElementClickable(DELETE_BUTTON_SUBMIT);
        actions.clickElement(DELETE_BUTTON_SUBMIT);
    }

    public void likeComment() {
        navigateToLatestPublicPost();

        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        actions.waitForElementClickable(COMMENT_LIKE_BUTTON);
        actions.clickElement(COMMENT_LIKE_BUTTON);

    }

    public void dislikeComment() {
        navigateToLatestPublicPost();

        actions.waitForElementClickable(COMMENT_SHOW_COMMENTS_BUTTON);
        actions.clickElement(COMMENT_SHOW_COMMENTS_BUTTON);

        actions.waitForElementClickable(COMMENT_DISLIKE_BUTTON);
        actions.clickElement(COMMENT_DISLIKE_BUTTON);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(Utils.getConfigPropertyByKey(POSTS_URL), currentUrl,
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + POSTS_URL);
    }

}


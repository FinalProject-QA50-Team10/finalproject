package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void createPost(String postType) {
        navigateToLatestPost();
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

    public void likePost() {
        navigateToLatestPost();
        actions.waitForElementClickable(POST_LIKE_BUTTON);
        actions.clickElement(POST_LIKE_BUTTON);
        actions.waitForElementPresent(POST_DISLIKE_BUTTON);
    }

    public void dislikePost() {
        navigateToLatestPost();
        actions.waitForElementClickable(POST_DISLIKE_BUTTON);
        actions.clickElement(POST_DISLIKE_BUTTON);
        actions.waitForElementPresent(POST_LIKE_BUTTON);
    }

    public void deletePost() {
        navigateToLatestPost();
        navigateToExplorePost();
        actions.waitForElementClickable(DELETE_POST_BUTTON);
        actions.clickElement(DELETE_POST_BUTTON);
        actions.waitForElementClickable(DELETE_DROP_DOWN_MENU);
        actions.clickElement(DELETE_DROP_DOWN_MENU);
        actions.waitForElementClickable(DELETE_BUTTON_SELECT);
        actions.clickElement(DELETE_BUTTON_SELECT);
        actions.waitForElementClickable(DELETE_BUTTON_SUBMIT);
        actions.clickElement(DELETE_BUTTON_SUBMIT);
    }

    public void editPost(String postType) {
        navigateToLatestPost();
        navigateToExplorePost();
        actions.waitForElementClickable(EDIT_POST_BUTTON);
        actions.clickElement(EDIT_POST_BUTTON);
        actions.waitForElementClickable(POSTS_VISIBILITY);
        actions.clickElement(POSTS_VISIBILITY);

        if (postType.equalsIgnoreCase("public")) {
            actions.waitForElementClickable(POSTS_PUBLIC_VISIBILITY);
            actions.clickElement(POSTS_PUBLIC_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(POST_EDIT_MESSAGE, POST_MESSAGE_FIELD);
        } else if (postType.equalsIgnoreCase("private")) {
            actions.waitForElementClickable(POSTS_PRIVATE_VISIBILITY);
            actions.clickElement(POSTS_PRIVATE_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(POST_EDIT_MESSAGE, POST_MESSAGE_FIELD);
        }

        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void createPostWithLongDescription(String postType, String longDescription) {
        navigateToLatestPost();
        actions.waitForElementClickable(POSTS_ADD_NEW_POST);
        actions.clickElement(POSTS_ADD_NEW_POST);
        actions.waitForElementClickable(POSTS_VISIBILITY);
        actions.clickElement(POSTS_VISIBILITY);

        if (postType.equalsIgnoreCase("public")) {
            actions.waitForElementClickable(POSTS_PUBLIC_VISIBILITY);
            actions.clickElement(POSTS_PUBLIC_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(longDescription, POST_MESSAGE_FIELD);
        } else if (postType.equalsIgnoreCase("private")) {
            actions.waitForElementClickable(POSTS_PRIVATE_VISIBILITY);
            actions.clickElement(POSTS_PRIVATE_VISIBILITY);
            actions.waitForElementClickable(POST_MESSAGE_FIELD);
            actions.typeValueInField(longDescription, POST_MESSAGE_FIELD);
        }

        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void assertPostText() {
        actions.waitForElementClickable(POST_TEXT);
        actions.assertElementPresent(POST_TEXT);
    }

    public void assertPostPrivateText() {
        actions.waitForElementClickable(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_PRIVATE_TEXT);
    }

    public void assertPostIsPublic() {
        actions.waitForElementClickable(POST_IS_PUBLIC);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }

    public void assertPostIsPrivate() {
        actions.waitForElementClickable(POST_IS_PRIVATE);
        actions.assertElementPresent(POST_IS_PRIVATE);
    }

    public void assertPostDislikeButton() {
        actions.waitForElementClickable(POST_DISLIKE_BUTTON);
        actions.assertElementPresent(POST_DISLIKE_BUTTON);
    }

    public void assertPostLikeButton() {
        actions.waitForElementClickable(POST_LIKE_BUTTON);
        actions.assertElementPresent(POST_LIKE_BUTTON);
    }

    public void assertPostInvalidTextMessage() {
        actions.waitForElementClickable(POST_INVALID_TEXT_MESSAGE);
        actions.assertElementPresent(POST_INVALID_TEXT_MESSAGE);
    }
    public void assertEditedPostText() {
        actions.waitForElementClickable(EDIT_POST_TEXT);
        actions.assertElementPresent(EDIT_POST_TEXT);
    }

    public void assertDeletePostMessage() {
        actions.waitForElementClickable(DELETE_POST_MESSAGE);
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    private void navigateToLatestPost() {
        actions.waitForElementClickable(LATEST_POST_BUTTON);
        actions.clickElement(LATEST_POST_BUTTON);
    }

    private void navigateToExplorePost() {
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
    }
}

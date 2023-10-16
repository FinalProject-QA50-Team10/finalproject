package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PersonalProfilePage extends BasePage {
    public PersonalProfilePage(WebDriver driver) {
        super(driver, PERSONAL_PROFILE_PAGE);
    }

    public void clickConnectButton() {
        actions.waitForElementClickable(CONNECT_BUTTON_PATH);
        actions.clickElement(CONNECT_BUTTON_PATH);
    }

    public void clickLogoutButton() {
        actions.waitForElementClickable(LOGOUT_BUTTON_PATH);
        actions.clickElement(LOGOUT_BUTTON_PATH);
    }

    public void clickNewFriendRequestsButton() {
        actions.waitForElementClickable(NEW_FRIEND_REQUESTS_BUTTON_PATH);
        actions.clickElement(NEW_FRIEND_REQUESTS_BUTTON_PATH);
    }

    public void clickNewestRequestApproveButton() {
        actions.waitForElementClickable(APPROVE_NEWEST_FRIEND_REQUESTS_BUTTON_PATH);
        actions.clickElement(APPROVE_NEWEST_FRIEND_REQUESTS_BUTTON_PATH);
    }

    public void clickDisconnectButton() {
        actions.waitForElementClickable(DISCONNECT_BUTTON_PATH);
        actions.clickElement(DISCONNECT_BUTTON_PATH);
    }

    public void clickEditProfileButton() {
        actions.waitForElementClickable(EDIT_PROFILE_BUTTON_PATH);
        actions.clickElement(EDIT_PROFILE_BUTTON_PATH);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(Utils.getConfigPropertyByKey(PERSONAL_PROFILE_PAGE)),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + PERSONAL_PROFILE_PAGE);
    }

    public void assertTextMessage() {
        actions.waitForElementVisible(CONNECT_MESSAGE_PATH);
        String textMessage = actions.getElement(CONNECT_MESSAGE_PATH).getText();
        Assertions.assertEquals(CONNECT_BUTTON_MESSAGE, textMessage,
                String.format("Expected message is different than actual. Expected: %s; Actual: %s",
                        CONNECT_MESSAGE_PATH, textMessage));
    }

    public void assertRequestsAreVisible() {
        actions.waitForElementClickable(APPROVE_FRIEND_REQUESTS_BUTTON_PATH);
        var requests = actions.getElements(APPROVE_FRIEND_REQUESTS_BUTTON_PATH);
        NUMBER_OF_FRIENDS_REQUESTS = requests.size();
        Assertions.assertFalse(requests.isEmpty(), "There are no friends request for approval.");
    }

    public void assertRequestsAreOneLess() {
        actions.waitForElementClickable(APPROVE_FRIEND_REQUESTS_BUTTON_PATH);
        var requests = actions.getElements(APPROVE_FRIEND_REQUESTS_BUTTON_PATH);
        Assertions.assertEquals(NUMBER_OF_FRIENDS_REQUESTS - 1, requests.size(),
                "Expected number of friend requests are different than actual.");
    }

    public void assertErrorMessage() {
        actions.assertElementPresent(ERROR_MESSAGE_FRIENDS_REQUESTS);
        var text = actions.getElement(ERROR_MESSAGE_FRIENDS_REQUESTS).getText();
        Assertions.assertEquals(REQUESTS_ERROR_MESSAGE, text, "Error message is different than actual.");
    }

    public void assertDisconnectButtonVisible() {
        actions.waitForElementVisible(DISCONNECT_BUTTON_PATH);
        actions.assertElementPresent(DISCONNECT_BUTTON_PATH);
    }


}

package test.cases.selenuim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.EMAIL_END;

public class FriendsRequestTests extends BaseTestSetup {

    @BeforeEach
    public void generateRandomUser() {
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        unauthenticatedUser.clickRegisterButton();
        registerPage.navigateToPage();
        registerPage.fillRegisterForm(RANDOM_USERNAME, RANDOM_EMAIL, GEORGE_BUSH_PASSWORD);
        registeredUserPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.login(RANDOM_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.navigateToHomePage();
        authenticatedUserHomePage.fillSearchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        searchedUsersPage.clickSeeProfileButton();
    }

    @AfterEach
    public void logOutAndNavigateToHomePage() {
        userProfilePage.clickLogoutButton();
        unauthenticatedUser.navigateToHomePage();
    }

    @Test
    //FPT1-116 [Friends Request] User sends a request to connect with another user
    public void when_AuthenticatedUserSendsRequestToConnectWithAnotherUser_Expect_SuccessfulMessageIsVisible() {
        userProfilePage.assertPageNavigated();
        userProfilePage.clickConnectButton();
        userProfilePage.assertTextMessage();
    }

    @Test
    //FPT1-116 [Friends Request] User sends a request to connect with another user
    public void when_AuthenticatedUserSendsRequestToConnectWithAnotherUser_Expect_SuccessfulAcceptedRequest() {
        userProfilePage.assertPageNavigated();
        userProfilePage.clickConnectButton();
        userProfilePage.assertTextMessage();
        userProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.clickPersonalProfileButton();
        userProfilePage.clickNewFriendRequestsButton();
        userProfilePage.assertRequestsAreVisible();
        userProfilePage.clickNewestRequestApproveButton();
        if (NUMBER_OF_FRIENDS_REQUESTS == 1) {
            userProfilePage.assertErrorMessage();
        } else {
            userProfilePage.assertRequestsAreOneLess();
        }
    }

    @Test
    //FPT1-117 [Friends Request] Disconnect users
    public void when_TwoUsersAreConnectedAndOneOfThemClickDisconnectButton_Expect_TheyAreNotConnected() {
        userProfilePage.assertPageNavigated();
        userProfilePage.clickConnectButton();
        userProfilePage.assertTextMessage();
        userProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.clickPersonalProfileButton();
        userProfilePage.clickNewFriendRequestsButton();
        userProfilePage.assertRequestsAreVisible();
        userProfilePage.clickNewestRequestApproveButton();
        userProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.login(RANDOM_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.fillSearchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        searchedUsersPage.clickSeeProfileButton();
        userProfilePage.assertDisconnectButtonVisible();
    }
}

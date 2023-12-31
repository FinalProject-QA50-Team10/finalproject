package test.cases.selenuim;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

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
        personalProfilePage.clickLogoutButton();
        unauthenticatedUser.navigateToHomePage();
    }

    @Test
    @Description("FPT1-116 [Friends Request] User sends a request to connect with another user")
    public void when_AuthenticatedUserSendsRequestToConnectWithAnotherUser_expect_SuccessfulMessageIsVisible() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickConnectButton();
        personalProfilePage.assertTextMessage();
    }

    @Test
    @Description("FPT1-116 [Friends Request] User sends a request to connect with another user")
    public void when_AuthenticatedUserSendsRequestToConnectWithAnotherUser_expect_SuccessfulAcceptedRequest() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickConnectButton();
        personalProfilePage.assertTextMessage();
        personalProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.clickPersonalProfileButton();
        personalProfilePage.clickNewFriendRequestsButton();
        personalProfilePage.assertRequestsAreVisible();
        personalProfilePage.clickNewestRequestApproveButton();
        if (NUMBER_OF_FRIENDS_REQUESTS == 1) {
            personalProfilePage.assertErrorMessage();
        } else {
            personalProfilePage.assertRequestsAreOneLess();
        }
    }

    @Test
    @Description("FPT1-116 [Friends Request] User sends a request to connect with another user")
    public void when_TwoUsersAreConnectedAndOneOfThemClickDisconnectButton_expect_DisconnectButtonIsVisible() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickConnectButton();
        personalProfilePage.assertTextMessage();
        personalProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.clickPersonalProfileButton();
        personalProfilePage.clickNewFriendRequestsButton();
        personalProfilePage.assertRequestsAreVisible();
        personalProfilePage.clickNewestRequestApproveButton();
        personalProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
        unauthenticatedUser.clickSignInButton();
        loginPage.login(RANDOM_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.fillSearchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        searchedUsersPage.clickSeeProfileButton();
        personalProfilePage.assertDisconnectButtonVisible();
    }
}

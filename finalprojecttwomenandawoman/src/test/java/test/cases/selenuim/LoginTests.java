package test.cases.selenuim;

import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import com.telerikacademy.testframework.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginTests extends BaseTestSetup {

    @BeforeEach
    public void navigateToLoginPage() {
        unauthenticatedUser.clickSignInButton();
        loginPage.navigateToPage();
    }

    @AfterEach
    public void navigateToHomePage() {
        loginPage.navigateToHomePage();
    }

    @Test
    //[Login Page] Verify Sing in button functionality and Login page elements
    public void when_UnauthenticatedUserNavigateToLoginPage_Expect_LoginPageElementsAreVisible() {
        loginPage.assertPageNavigated();
        loginPage.assertLoginPageTitle();
        loginPage.assertPasswordFieldVisible();
        loginPage.assertUsernameFieldVisible();
        loginPage.assertLoginButtonVisible();
    }

    @Test
    //[Login Page] Login with valid username and valid password
    public void when_UnauthenticatedUserLoginWithValidCredentials_Expect_SuccessfulHomePageElementsAreVisible() {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.assertPersonalProfileButtonIsVisible();
        authenticatedUserHomePage.assertAddNewPostButtonIsVisible();
        authenticatedUserHomePage.logout();
        unauthenticatedUser.assertPageNavigated();
    }

    @Test
    //[Login Page] Try to log in with an empty username and an empty password
    public void when_UnauthenticatedUserLoginWithEmptyUsernameAndEmptyPassword_Expect_ErrorMessage() {
        loginPage.assertPageNavigated();
        loginPage.login(EMPTY_STRING, EMPTY_STRING);
        loginPage.assertPageNavigated();
        loginPage.assertLoginPageTitle();
        loginPage.assertLoginErrorMessage();
    }

    @Test
    //[Login Page] Try to log in with a valid username and invalid password
    public void when_UnauthenticatedUserLoginWithValidUsernameAndInvalidPassword_Expect_ErrorMessage() {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, MR_BEAST_PASSWORD);
        loginPage.assertPageNavigated();
        loginPage.assertLoginErrorMessage();
        loginPage.assertLoginPageTitle();
    }

    @Test
    //[Login Page] Try to log in with invalid username and valid password
    public void when_UnauthenticatedUserLoginWithInvalidUsernameAndValidPassword_Expect_ErrorMessage() {
        loginPage.assertPageNavigated();
        loginPage.login(INVALID_USERNAME, MR_BEAST_PASSWORD);
        loginPage.assertPageNavigated();
        loginPage.assertLoginErrorMessage();
        loginPage.assertLoginPageTitle();
    }

    @Test
    //[Login Page] Verify the Logout button functionality
    public void when_AuthenticatedUserClickLogoutButton_Expect_SuccessfulLogout() {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        authenticatedUserHomePage.assertLogoutButtonIsVisible();
        authenticatedUserHomePage.assertPersonalProfileButtonIsVisible();
        authenticatedUserHomePage.assertAddNewPostButtonIsVisible();
        authenticatedUserHomePage.logout();
        unauthenticatedUser.assertPageNavigated();
        loginPage.assertLogoutErrorMessage();
        loginPage.assertLoginPageTitle();
    }
}

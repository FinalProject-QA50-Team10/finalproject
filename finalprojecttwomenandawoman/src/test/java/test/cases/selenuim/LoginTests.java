package test.cases.selenuim;

import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import com.telerikacademy.testframework.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginTests extends BaseTestSetup{

    @BeforeEach
    public void navigateToLoginPage()
    {
        unauthenticatedUser.clickSignInButton();
        loginPage.navigateToPage();
    }

    @AfterEach
    public void navigateToHomePage()
    {
        loginPage.navigateToHomePage();
    }
    @Test
    //[Login Page] Verify Sing in button functionality and Login page elements
    public void when_UnauthenticatedUserNavigateToLoginPage_Expect_LoginPageElementsAreVisible()
    {
        loginPage.assertPageNavigated();
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
        actions.assertElementPresent(LOGIN_PASSWORD_FIELD_PATH);
        actions.assertElementPresent(LOGIN_USERNAME_FIELD_PATH);
        actions.assertElementPresent(LOGIN_BUTTON_PATH);
    }

    @Test
    //[Login Page] Login with valid username and valid password
    public void when_UnauthenticatedUserLoginWithValidCredentials_Expect_SuccessfulHomePageElementsAreVisible()
    {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
        loginPage.logout();
    }

    @Test
    //[Login Page] Try to log in with an empty username and an empty password
    public void when_UnauthenticatedUserLoginWithEmptyUsernameAndEmptyPassword_Expect_ErrorMessage()
    {
        loginPage.assertPageNavigated();
        loginPage.login(EMPTY_STRING, EMPTY_STRING);
        actions.assertElementPresent(LOGIN_ERROR_MESSAGE_PATH);
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
    }

    @Test
    //[Login Page] Try to log in with a valid username and invalid password
    public void when_UnauthenticatedUserLoginWithValidUsernameAndInvalidPassword_Expect_ErrorMessage()
    {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, MR_BEAST_PASSWORD);
        actions.assertElementPresent(LOGIN_ERROR_MESSAGE_PATH);
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
    }

    @Test
    //[Login Page] Try to log in with invalid username and valid password
    public void when_UnauthenticatedUserLoginWithInvalidUsernameAndValidPassword_Expect_ErrorMessage()
    {
        loginPage.assertPageNavigated();
        loginPage.login(INVALID_USERNAME, MR_BEAST_PASSWORD);
        actions.assertElementPresent(LOGIN_ERROR_MESSAGE_PATH);
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
    }

    @Test
    //[Login Page] Verify the Logout button functionality
    public void when_AuthenticatedUserClickLogoutButton_Expect_SuccessfulLogout()
    {
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
        loginPage.logout();
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
        actions.assertElementPresent(LOGOUT_ERROR_MESSAGE_PATH);
    }
}

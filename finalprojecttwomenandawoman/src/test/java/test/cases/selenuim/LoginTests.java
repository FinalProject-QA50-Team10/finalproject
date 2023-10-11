package test.cases.selenuim;

import com.telerikacademy.testframework.pages.AuthenticatedUserHomePage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import com.telerikacademy.testframework.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginTests extends BaseTestSetup{
    LoginPage loginPage = new LoginPage(actions.getDriver());
    UnauthenticatedUserHomePage homePage = new UnauthenticatedUserHomePage(actions.getDriver());

    @BeforeEach
    public void navigateToLoginPage()
    {
        homePage.clickSignInButton();
        loginPage.navigateToPage();
    }

    @Test
    public void when_unauthenticatedUserLoginWithValidCredentials_expect_successHomePageElementsAreVisible()
    {
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        actions.assertElementPresent(LOGOUT_BUTTON);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON);
    }
}

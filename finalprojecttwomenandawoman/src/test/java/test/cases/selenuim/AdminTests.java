package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.AdminHomePage;
import com.telerikacademy.testframework.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AdminTests {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    AdminHomePage adminHomePage = new AdminHomePage(actions.getDriver());
    static UserActions actions = new UserActions();


    @BeforeEach
    public void loginUserAdmin() {
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADMIN_ZONE_BUTTON);
    }

    @AfterEach
    public void logOut() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
        actions.waitForElementClickable(LOG_OUT_BUTTON_PATH);
        actions.clickElement(LOG_OUT_BUTTON_PATH);
    }

    @Test
    //FPT1-58 [See Profile] Disable User Profile as Admin
    public void when_adminDisableUsers_expected_userIsDisable() {
        adminHomePage.validateAdminCanDisableUser();
        actions.waitForElementPresent(ENABLE_USER_BUTTON);
        actions.assertElementPresent(ENABLE_USER_BUTTON);
    }
}

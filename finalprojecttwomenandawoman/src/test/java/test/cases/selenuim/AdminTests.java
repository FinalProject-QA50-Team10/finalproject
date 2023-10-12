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
    AdminHomePage admin = new AdminHomePage(actions.getDriver());
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
        actions.assertElementPresent(LOGIN_PAGE_TITLE_PATH);
        actions.assertElementPresent(LOGOUT_ERROR_MESSAGE_PATH);
    }

    @Test
    //FPT1-58 [See Profile] Disable User Profile as Admin
    public void when_AdminDisableUsers_expected_UserIsDisable() {
        admin.disableUserWithAdminAccess();
        actions.waitForElementPresent(ENABLE_USER_BUTTON);
        actions.assertElementPresent(ENABLE_USER_BUTTON);
    }

    @Test
    //FPT1-59 [See Profile] Enable User Profile as Admin
    public void when_AdminEnableUser_expected_UserIsEnable() {
        admin.enableUserWithAdminAccess();
        actions.waitForElementPresent(DISABLE_USER_BUTTON);
        actions.assertElementPresent(DISABLE_USER_BUTTON);
    }


}

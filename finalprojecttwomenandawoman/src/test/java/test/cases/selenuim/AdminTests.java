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
        loginPage.login("WEare.admin.username", "WEare.admin.password");
        actions.assertElementPresent(LOGOUT_BUTTON);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON);
        actions.assertElementPresent(ADMIN_ZONE_BUTTON);
    }

    @AfterEach
    public void returnHome() {
        actions.waitForElementClickable(HOME_BUTTON);
        actions.clickElement(HOME_BUTTON);
        actions.waitForElementClickable(LOG_OUT_BUTTON);
        actions.clickElement(LOG_OUT_BUTTON);
    }

    @Test
    //FPT1-58 [See Profile] Disable User Profile as Admin
    public void when_adminDisableUsers_expected_userIsDisable() {
        adminHomePage.validateAdminCanDisableUser();
        actions.waitForElementPresent(ENABLE_USER_BUTTON);
        actions.assertElementPresent(ENABLE_USER_BUTTON);
    }
}

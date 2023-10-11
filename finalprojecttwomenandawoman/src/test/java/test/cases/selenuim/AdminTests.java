package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.telerikacademy.testframework.pages.Constants.*;
import static test.cases.selenuim.BaseTestSetup.actions;

public class AdminTests {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    static UserActions actions = new UserActions();


    @BeforeEach
    public void loginUserAdmin() {
        loginPage.login("WEare.admin.username", "WEare.admin.password");
        actions.assertElementPresent(LOGOUT_BUTTON);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON);
        actions.assertElementPresent(ADMIN_DASHBOARD_BUTTON);
    }

    @AfterEach
    public void returnHome() {
        actions.waitForElementClickable(HOME_BUTTON);
        actions.clickElement(HOME_BUTTON);
        actions.waitForElementClickable(LOG_OUT_BUTTON);
        actions.clickElement(LOG_OUT_BUTTON);
    }
}

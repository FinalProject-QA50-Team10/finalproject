package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.AdminHomePage;
import com.telerikacademy.testframework.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AdminTests extends BaseTestSetup{


    AdminHomePage admin = new AdminHomePage(actions.getDriver());
    static UserActions actions = new UserActions();


    @BeforeEach
    public void loginUserAdmin() {
        loginPage.navigateToPage();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
    }

    @AfterEach
    public void logOut() {
        authenticatedUserHomePage.logout();
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

    @Test
    //FTP1-60 [Edit Post] Edit an Existing Post as Admin
    public void when_AdminEditsPost_expect_PostIsEdited() {

    }
}

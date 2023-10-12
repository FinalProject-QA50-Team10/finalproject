package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.PostPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PostTests extends BaseTestSetup {

    PostPage post = new PostPage(actions.getDriver());

    @BeforeEach
    public void login() {
        post.navigateToPage();
        post.assertPageNavigated();
        LoginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
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
    public void createPublicPost() {

    }
}

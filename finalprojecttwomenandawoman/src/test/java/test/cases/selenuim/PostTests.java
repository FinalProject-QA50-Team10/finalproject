package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.PostPage;
import groovy.util.logging.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PostTests extends BaseTestSetup {

    PostPage post = new PostPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());

    @BeforeEach
    public void login() {
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
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
    public void when_UserCreatePublicPost_expect_PublicPostIsCreated() {
        post.createPublicPost();
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }
}

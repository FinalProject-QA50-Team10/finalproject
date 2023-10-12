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
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
    }

    @AfterEach
    public void logOut() {
        loginPage.logout();
    }

    @Test
    public void when_UserCreatePublicPost_expect_PublicPostIsCreated() {
        post.createPublicPost();
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }
}

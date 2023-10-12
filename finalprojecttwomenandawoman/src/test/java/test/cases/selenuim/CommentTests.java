package test.cases.selenuim;

import com.telerikacademy.testframework.pages.CommentPage;
import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.PostPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class CommentTests extends BaseTestSetup {

    CommentPage commentPage = new CommentPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @BeforeEach
    public void setup() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        loginPage.logout();
    }

    @AfterEach
    public void teardown() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        loginPage.logout();
    }

    @Test
    public void testCreateCommentSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT_FIELD);
        loginPage.logout();
    }
}

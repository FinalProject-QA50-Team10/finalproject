package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.PostPage;
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
    // FPT1-25 [Add New Post] Create Public Post
    public void when_UserCreatePublicPost_expect_PublicPostIsCreated() {
        post.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }

    @Test
    // FTP1-26 [Add New Post] Create Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsCreated() {
        post.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_UserCreatePublicPost_expect_PublicPostIsDeleted() {
        post.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        post.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsDeleted() {
        post.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
        post.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-45 [Edit Post] Edit an Existing Public Post
    public void when_UserEditPublicPost_expect_PublicPostIsEdited() {
        post.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        post.editPost();
        actions.assertElementPresent(POST_EDIT_MESSAGE);
    }
}

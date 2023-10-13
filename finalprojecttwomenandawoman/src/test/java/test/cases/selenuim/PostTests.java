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
        post.editPublicPost();
        actions.assertElementPresent(EDIT_POST_TEXT);
        post.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-46 [Edit Post]  Edit an Existing Private Post
    public void when_UserEditPrivatePost_expect_PrivatePostIsEdited() {
        post.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
        post.editPrivatePost();
        actions.assertElementPresent(EDIT_POST_TEXT);
        post.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-123 [Like] Like a public post of another user
    public void when_UserLikePublicPost_expected_PostIsLiked() {
        post.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        post.likePost();
        actions.assertElementPresent(POST_DISLIKE_BUTTON);
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        post.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }
}

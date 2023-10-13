package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.PostPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class PostTests extends BaseTestSetup {

    @BeforeEach
    public void login() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
    }

    @AfterEach
    public void logOut() {
        authenticatedUserHomePage.logout();
    }

    @Test
    // FPT1-25 [Add New Post] Create Public Post
    public void when_UserCreatePublicPost_expect_PublicPostIsCreated() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }

    @Test
    // FTP1-26 [Add New Post] Create Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsCreated() {
        postPage.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_UserCreatePublicPost_expect_PublicPostIsDeleted() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsDeleted() {
        postPage.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-45 [Edit Post] Edit an Existing Public Post
    public void when_UserEditPublicPost_expect_PublicPostIsEdited() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        postPage.editPublicPost();
        actions.assertElementPresent(EDIT_POST_TEXT);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-46 [Edit Post]  Edit an Existing Private Post
    public void when_UserEditPrivatePost_expect_PrivatePostIsEdited() {
        postPage.createPost("private");
        actions.assertElementPresent(POST_PRIVATE_TEXT);
        actions.assertElementPresent(POST_IS_PRIVATE);
        postPage.editPrivatePost();
        actions.assertElementPresent(EDIT_POST_TEXT);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-123 [Like] Like a public post of another user
    public void when_UserLikePublicPost_expected_PostIsLiked() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.likePost();
        actions.assertElementPresent(POST_DISLIKE_BUTTON);
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }
}

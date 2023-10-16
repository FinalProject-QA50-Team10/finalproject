package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
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
        postPage.assertPostText();
        postPage.assertPostIsPublic();
    }

    @Test
    // FTP1-26 [Add New Post] Create Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsCreated() {
        postPage.createPost("private");
        postPage.assertPostPrivateText();
        postPage.assertPostIsPrivate();
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_UserCreatePublicPost_expect_PublicPostIsDeleted() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_UserCreatePrivatePost_expect_PrivatePostIsDeleted() {
        postPage.createPost("private");
        postPage.assertPostPrivateText();
        postPage.assertPostIsPrivate();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-45 [Edit Post] Edit an Existing Public Post
    public void when_UserEditPublicPost_expect_PublicPostIsEdited() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        postPage.editPost("public");
        postPage.assertEditedPostText();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-46 [Edit Post]  Edit an Existing Private Post
    public void when_UserEditPrivatePost_expect_PrivatePostIsEdited() {
        postPage.createPost("private");
        postPage.assertPostPrivateText();
        postPage.assertPostIsPrivate();
        postPage.editPost("private");
        postPage.assertEditedPostText();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-123 [Like] Like a public post of another user
    public void when_UserLikePublicPost_expect_PostIsLiked() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.likePost();
        actions.assertElementPresent(POST_DISLIKE_BUTTON);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FTP1-124 [Like] Dislike a public post of another user
    public void when_UserDislikePublicPost_expect_PostIsDisliked() {
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.likePost();
        actions.assertElementPresent(POST_DISLIKE_BUTTON);
        postPage.dislikePost();
        actions.assertElementPresent(POST_LIKE_BUTTON);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-35 [Add New Post] Create Public Post with a Description Length of 1001 symbols
    public void when_UserCreatePublicPostWithInvalidData_expect_PostNotCreated() {
        String longDescription = UserActions.generateLongString();
        postPage.createPostWithLongDescription("public", longDescription);
        actions.assertElementPresent(POST_INVALID_TEXT_MESSAGE);
    }

    @Test
    //FPT1-36 [Add New Post] Create Private Post with a Description Length of 1001 symbols
    public void when_UserCreatePrivatePostWithInvalidData_expect_PostNotCreated() {
        String longDescription = UserActions.generateLongString();
        postPage.createPostWithLongDescription("private", longDescription);
        actions.assertElementPresent(POST_INVALID_TEXT_MESSAGE);
    }
}

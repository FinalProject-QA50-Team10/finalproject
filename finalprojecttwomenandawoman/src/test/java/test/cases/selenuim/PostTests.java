package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import jdk.jfr.Description;
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
    @Description("FPT1-25 [Add New Post] Create Public Post")
    public void when_UserCreatePublicPost_expect_PublicPostIsCreated() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
    }

    @Test
    @Description("FTP1-26 [Add New Post] Create Private Post")
    public void when_UserCreatePrivatePost_expect_PrivatePostIsCreated() {
        postPage.createPost("private");
        postPage.assertPostPrivateText();
        postPage.assertPostIsPrivate();
    }

    @Test
    @Description("FTP1-33 [Add New Post] Create Public Post with a Blank Description")
    public void when_UserCreatePublicPostWithBlankDescription_expect_PublicPostIsCreated() {
        postPage.createPostWithBlankDescription("public");
        postPage.assertPostEmptyText();
        postPage.assertPostIsPublic();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FTP1-34 [Add New Post] Create Private Post with a Blank Description")
    public void when_UserCreatePrivatePostWithBlankDescription_expect_PrivatePostIsCreated() {
        postPage.createPostWithBlankDescription("private");
        postPage.assertPostEmptyText();
        postPage.assertPostIsPrivate();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FPT1-55 [Delete Post] Delete an Existing Public Post")
    public void when_UserCreatePublicPost_expect_PublicPostIsDeleted() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FPT1-56 [Delete Post] Delete an Existing Private Post")
    public void when_UserCreatePrivatePost_expect_PrivatePostIsDeleted() {
        postPage.createPost("private");
        postPage.assertPostPrivateText();
        postPage.assertPostIsPrivate();
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FPT1-45 [Edit Post] Edit an Existing Public Post")
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
    @Description("FPT1-46 [Edit Post]  Edit an Existing Private Post")
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
    @Description("FPT1-123 [Like] Like a public post of another user")
    public void when_UserLikePublicPost_expect_PostIsLiked() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.likePost();
        postPage.assertPostDislikeButton();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FTP1-124 [Like] Dislike a public post of another user")
    public void when_UserDislikePublicPost_expect_PostIsDisliked() {
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.likePost();
        postPage.assertPostDislikeButton();
        postPage.dislikePost();
        postPage.assertPostLikeButton();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        postPage.assertDeletePostMessage();
    }

    @Test
    @Description("FPT1-35 [Add New Post] Create Public Post with a Description Length of 1001 symbols")
    public void when_UserCreatePublicPostWithInvalidData_expect_PostNotCreated() {
        String longDescription = UserActions.generateLongString();
        postPage.createPostWithLongDescription("public", longDescription);
        postPage.assertPostInvalidTextMessage();
    }

    @Test
    @Description("FPT1-36 [Add New Post] Create Private Post with a Description Length of 1001 symbols")
    public void when_UserCreatePrivatePostWithInvalidData_expect_PostNotCreated() {
        String longDescription = UserActions.generateLongString();
        postPage.createPostWithLongDescription("private", longDescription);
        postPage.assertPostInvalidTextMessage();
    }
}

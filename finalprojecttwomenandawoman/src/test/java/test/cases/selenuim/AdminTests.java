package test.cases.selenuim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AdminTests extends BaseTestSetup {

    @BeforeEach
    public void loginUserAdmin() {
        loginPage.navigateToPage();
    }

    @AfterEach
    public void logOut() {
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-58 [See Profile] Disable User Profile as Admin
    //FPT1-59 [See Profile] Enable User Profile as Admin
    public void when_AdminDisableAndEnableUsers_expected_UserIsDisableAndEnable() {
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        adminHomePage.disableUserWithAdminAccess();
        adminHomePage.assertEnableUserButton();
        adminHomePage.enableUserWithAdminAccess();
        adminHomePage.assertDisableUserButton();
    }

    @Test
    //FTP1-60 [Edit Post] Edit an Existing Post as Admin
    public void when_AdminEditsPost_expect_PostIsEdited() {
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        adminHomePage.editPostWithAdminAccess();
        postPage.assertEditedPostText();
        adminHomePage.deletePostWithAdminAccess();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FTP1-61 [Delete Post] Delete an Existing Post as Admin
    public void when_AdminDeletePost_expect_PostIsDeleted() {
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        adminHomePage.deletePostWithAdminAccess();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_AdminEditComment_expect_CommentIsEdited() {
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        commentPage.editComment("This is an edited comment.");
        commentPage.assertCommentEdited();
        adminHomePage.deletePostWithAdminAccess();
        postPage.assertDeletePostMessage();
    }

    @Test
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_AdminDeleteComment_expect_CommentIsDeleted() {
        loginPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        postPage.assertPostText();
        postPage.assertPostIsPublic();
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        commentPage.deleteComment();
        commentPage.assertCommentDeleted();
        adminHomePage.deletePostWithAdminAccess();
        postPage.assertDeletePostMessage();
    }
}

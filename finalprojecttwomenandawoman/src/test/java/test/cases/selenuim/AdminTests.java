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
        actions.waitForElementPresent(ENABLE_USER_BUTTON);
        actions.assertElementPresent(ENABLE_USER_BUTTON);
        adminHomePage.enableUserWithAdminAccess();
        actions.waitForElementPresent(DISABLE_USER_BUTTON);
        actions.assertElementPresent(DISABLE_USER_BUTTON);
    }

    @Test
    //FTP1-60 [Edit Post] Edit an Existing Post as Admin
    public void when_AdminEditsPost_expect_PostIsEdited() {
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        adminHomePage.editPostWithAdminAccess();
        actions.assertElementPresent(EDIT_POST_TEXT);
        adminHomePage.deletePostWithAdminAccess();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FTP1-61 [Delete Post] Delete an Existing Post as Admin
    public void when_AdminDeletePost_expect_PostIsDeleted() {
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        adminHomePage.deletePostWithAdminAccess();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_AdminEditComment_expect_CommentIsEdited() {
        postPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        commentPage.editComment("This is an edited comment.");
        actions.assertElementPresent(COMMENT_TEXT_FIELD);
        adminHomePage.deletePostWithAdminAccess();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }

    @Test
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_AdminDeleteComment_expect_CommentIsDeleted() {
        loginPage.assertPageNavigated();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        actions.assertElementPresent(POST_TEXT);
        actions.assertElementPresent(POST_IS_PUBLIC);
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
        loginPage.login(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT);
        loginPage.navigateToPage();
        postPage.assertPageNavigated();
        loginPage.login(ADMIN_NAME, ADMIN_PASSWORD);
        commentPage.deleteComment();
        actions.assertElementPresent(DELETE_COMMENT_MESSAGE);
        adminHomePage.deletePostWithAdminAccess();
        actions.assertElementPresent(DELETE_POST_MESSAGE);
    }
}

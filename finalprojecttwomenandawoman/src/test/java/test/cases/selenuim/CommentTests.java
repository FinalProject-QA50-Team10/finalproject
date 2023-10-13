package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class CommentTests extends BaseTestSetup {

    @BeforeEach
    public void setup() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        authenticatedUserHomePage.logout();
    }

    @AfterEach
    public void teardown() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void when_UserCreatesComment_expect_CommentIsCreatedSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        actions.assertElementPresent(COMMENT_TEXT_FIELD);

        //add more assertions
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-170 [Comment] Create Comment With 1001 Characters as Registered User
    public void when_UserCreatesCommentWithInvalidData_expect_CommentCreationFails() {
        UserActions utils = new UserActions();
        String commentText = UserActions.generateRandomTextExactLength(1001);

        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment(commentText);

        //assert invalid message
        utils.assertElementPresent(COMMENT_TEXT_FIELD); //refactor actions
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-178 [Comment] Edit Comment Successfully as Registered User
    public void when_UserEditsComment_expect_CommentIsEditedSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        commentPage.editComment("This is an edited comment.");
        actions.assertElementPresent(COMMENT_TEXT_FIELD);
        //add more assertions
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-182 [Comment] Delete Comment Successfully as Registered User
    public void when_UserDeletesComment_expect_CommentIsDeletedSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        commentPage.deleteComment();
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-125 [Like] Verify comments Like button
    public void when_UserLikesComment_expect_CommentIsLikedSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        commentPage.likeComment();
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-126 [Like] Verify comments Dislike button
    public void when_UserDislikesComment_expect_CommentIsDislikedSuccessfully() {
        loginPage.navigateToPage();
        loginPage.login(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        commentPage.createComment("This is a comment.");
        commentPage.likeComment();
        commentPage.dislikeComment();
        authenticatedUserHomePage.logout();

    }

}

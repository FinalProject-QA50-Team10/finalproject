package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(OrderAnnotation.class)
public class CommentTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private static int lastPostId;
    private static int lastCommentId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Login with valid username and valid password - JackNicholson
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserJackNicholson = posts.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Login with valid username and valid password - TomCruise
    public void when_anotherUserSignsIn_expected_loginSuccessful() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void when_TomCruiseCreatesComment_expected_CommentIsCreated() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
        Response createCommentResponse = posts.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);
        posts.assertStatusCodeIsOk(createCommentResponse.statusCode());
        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");
        //add more assertions
    }

    @Test
    @Order(5)
    //FPT1-178 [Comment] Edit Comment Successfully as Registered User
    public void when_TomCruiseEditsComment_expected_CommentIsEdited() {
        Response editCommentResponse = posts.editComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);
        posts.assertStatusCodeIsOk(editCommentResponse.statusCode());
        //аdd more assertions
    }

    @Test
    @Order(6)
    //FPT1-182 [Comment] Delete Comment Successfully as Registered User
    public void when_TomCruiseDeletesComment_expected_CommentIsDeleted() {
        Response deleteCommentResponse = posts.deleteComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, lastCommentId);
        posts.assertStatusCodeIsOk(deleteCommentResponse.statusCode());
        //аdd more assertions
    }

    @Test
    @Order(7)
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_JackNicholsonDeletesPost_expected_PostIsDeleted() {
        Response deletePostResponse = posts.deletePublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        posts.assertStatusCodeIsOk(deletePostResponse.statusCode());
        //аdd more assertions
    }

}

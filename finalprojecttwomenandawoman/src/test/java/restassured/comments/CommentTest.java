package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
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

        //check status code
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);

        //assert status code
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());

        //assert post content
        posts.assertPostContent(createNewPublicPost, "Valid Post");

        //assert post is public
        posts.assertPostIsPublic(createNewPublicPost);

        //assert post id is not null
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        Assertions.assertTrue(postId > 0, "Post ID is not a positive number.");

        //assert post content is as expected
        String content = createNewPublicPost.jsonPath().getString("content");
        Assertions.assertEquals("Valid Post", content, "Content is not as expected.");

        //assert category id match
        int categoryId = createNewPublicPost.jsonPath().getInt("category.id");
        Assertions.assertEquals(102, categoryId, "Category ID is not as expected.");

        //assert category name match
        String categoryName = createNewPublicPost.jsonPath().getString("category.name");
        Assertions.assertEquals("Actor", categoryName, "Category name is not as expected.");

        //save post id
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Login with valid username and valid password - TomCruise
    public void when_anotherUserSignsIn_expected_loginSuccessful() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);

        //check status code
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void when_TomCruiseCreatesComment_expected_CommentIsCreated() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);

        //check status code
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
        Response createCommentResponse = posts.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);

        //check status code
        posts.assertStatusCodeIsOk(createCommentResponse.statusCode());

        //save comment id
        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");

        //assert comment match
        String commentContent = createCommentResponse.jsonPath().getString("content");
        Assertions.assertEquals("Valid Comment", commentContent, "Comment content does not match.");
    }


    @Test
    @Order(5)
    //FPT1-178 [Comment] Edit Comment Successfully as Registered User
    public void when_TomCruiseEditsComment_expected_CommentIsEdited() {
        Response editCommentResponse = posts.editComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);

        //check status code
        posts.assertStatusCodeIsOk(editCommentResponse.statusCode());
    }

    @Test
    @Order(6)
    //FPT1-182 [Comment] Delete Comment Successfully as Registered User
    public void when_TomCruiseDeletesComment_expected_CommentIsDeleted() {
        Response deleteCommentResponse = posts.deleteComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, lastCommentId);

        //check status code
        posts.assertStatusCodeIsOk(deleteCommentResponse.statusCode());

        //check response body is empty
        String responseBody = deleteCommentResponse.getBody().asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body is not empty.");
    }

    @Test
    @Order(7)
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_JackNicholsonDeletesPost_expected_PostIsDeleted() {
        Response deletePostResponse = posts.deletePublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);

        //check status code
        posts.assertStatusCodeIsOk(deletePostResponse.statusCode());

        //check response body is empty
        String responseBody = deletePostResponse.getBody().asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body is not empty.");
    }

}

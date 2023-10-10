package restassured.comments;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.CommentModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminCommentEditDeleteTest {
    private final BaseSetupMethods comments = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;
    private static int lastCommentId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Login with valid username and valid password - JackNicholson
    public void when_userSignsIn_expect_loginSuccessful() {
        Response signInWithUserJackNicholson = comments.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        assertions.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expect_newPublicPostCreated() {
        Response createNewPublicPost = comments.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);

        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);

        String content = createNewPublicPost.jsonPath().getString("content");
        assertions.assertContentIsExpected(content, "Valid Post");

        int categoryId = createNewPublicPost.jsonPath().getInt("category.id");
        assertions.assertCategoryIdIsExpected(categoryId, 102);

        String categoryName = createNewPublicPost.jsonPath().getString("category.name");
        assertions.assertCategoryNameIsExpected(categoryName, "Actor");

        lastPostId = postId;
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Login with valid username and valid password - TomCruise
    public void when_anotherUserSignsIn_expect_loginSuccessful() {
        Response signInWithUserTomCruise = comments.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void when_userCreatesComment_expect_commentIsCreated() {
        Response signInWithUserTomCruise = comments.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = comments.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);

        assertions.assertStatusCode200(createCommentResponse.statusCode());

        var t = createCommentResponse.as(CommentModel.class);

        String commentContent = t.content;
        assertions.assertContentIsExpected(commentContent, "Valid Comment");

        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");
    }

    @Test
    @Order(5)
    //[Login Form] Login with a valid username and a valid password as an admin user
    public void when_adminUserSignsIn_expect_loginSuccessful() {
        Response signInWithUserAdmin = comments.signInUser(ADMIN_NAME, ADMIN_PASSWORD);
        assertions.assertStatusCode302(signInWithUserAdmin.statusCode());
    }

    @Test
    @Order(6)
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_adminEditsComment_expect_commentIsEdited() {
        Response editCommentResponse = comments.editComment(ADMIN_NAME, ADMIN_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());
    }

    @Test
    @Order(7)
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_adminDeletesComment_expect_commentIsDeleted() {
        Response deleteCommentResponse = comments.deleteComment(ADMIN_NAME, ADMIN_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);
    }

    @Test
    @Order(7)
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletesPost_expect_postIsDeleted() {
        Response deletePostResponse = comments.deletePost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePostResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePostResponse);
    }

}

package restassured.posts;

import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostPostTest extends BasePostTestSetupBeforeAfter {

    @Test
    //FPT1-26 [Add New Post] Create Private Post
    public void when_UserCreatesValidPrivatePost_expect_ValidPrivatePostIsCreated() {
        Response createNewPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");

        Response deletePrivatePost = apiMethods.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    //FTP1-36 [Add New Post] Generate new invalid private post
    public void when_UserCreatesInvalidPrivatePost_expect_InvalidPrivatePostIsNotCreated() {
        Response createNewInvalidPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_INVALID);
        assertions.assertStatusCode400(createNewInvalidPrivatePost.statusCode());

        var registrationErrorModel = createNewInvalidPrivatePost.as(ErrorModel.class);
        assertions.assertBadRequest(registrationErrorModel);
        assertions.assertInvalidPostContent(createNewInvalidPrivatePost);
    }

    @Test
    //FTP1-46 [Edit Post] Edit existing private post
    public void when_UserEditsPrivatePost_expect_PrivatePostIsEdited() {
        Response createNewPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");

        Response editPrivatePost = apiMethods.editPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                EDIT_POST_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(editPrivatePost.statusCode());

        Response deletePrivatePost = apiMethods.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_UserDeletesPrivatePost_expect_PrivatePostIsDeleted() {
        Response createNewPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");

        Response deletePrivatePost = apiMethods.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

}

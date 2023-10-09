package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrivatePostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Sign in with username GeorgeBush
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-26 [Add New Post] Generate new valid private post
    public void when_userCreatePrivatePost_expected_newPrivatePostCreated() {
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent(createNewPrivatePost, "Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FTP1-36 [Add New Post] Generate new invalid private post
    public void when_userCreateInvalidPrivatePost_expect_invalidPrivatePostNotBeCreated() {
        Response createNewInvalidPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_INVALID);
        var registrationErrorModel = createNewInvalidPrivatePost.as(ErrorModel.class);
        assertions.assertStatusCode400(createNewInvalidPrivatePost.statusCode());
        assertions.assertBadRequest(registrationErrorModel);
        assertions.assertInvalidPostContent(createNewInvalidPrivatePost);
    }

    @Test
    @Order(3)
    //FTP1-46 [Edit Post] Edit existing private post
    public void when_userEditPrivatePost_expect_postIsEdited() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response editPrivatePost = posts.editPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, EDIT_POST_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(editPrivatePost.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-56 [Delete Post] Confirming user can delete his own private post
    public void when_userDeletePrivatePost_expected_deleteLatestPrivatePost() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePrivatePost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    @Order(5)
    public void when_userSignIn_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = posts.getLastPublicPost(lastPostId);
        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

package restassured.posts;

import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostPostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPrivatePost();
    }

    @AfterEach
    public void teardown() {
        deletePrivatePost();
    }

    @Test
    //FTP1-36 [Add New Post] Generate new invalid private post
    public void when_userCreateInvalidPrivatePost_expect_invalidPrivatePostNotBeCreated() {
        Response createNewInvalidPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_INVALID);

        // Assert that the HTTP status code is 400 (Bad Request)
        assertions.assertStatusCode400(createNewInvalidPrivatePost.statusCode());

        // Assert that the body contains the expected error model
        var registrationErrorModel = createNewInvalidPrivatePost.as(ErrorModel.class);
        assertions.assertBadRequest(registrationErrorModel);

        // Assert that the post content is invalid
        assertions.assertInvalidPostContent(createNewInvalidPrivatePost);
    }

    @Test
    //FTP1-46 [Edit Post] Edit existing private post
    public void when_userEditPrivatePost_expect_postIsEdited() {

        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = apiMethods.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInResponse.statusCode());

        // Edit the existing private post
        Response editPrivatePost = apiMethods.editPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                EDIT_POST_DESCRIPTION_VALID, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(editPrivatePost.statusCode());
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_userDeletePost_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = apiMethods.getLastPost(lastPostId);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

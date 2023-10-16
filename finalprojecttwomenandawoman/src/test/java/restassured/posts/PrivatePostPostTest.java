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

        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = apiMethods.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);

        assertions.assertStatusCode302(signInResponse.statusCode());

        Response editPrivatePost = apiMethods.editPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                EDIT_POST_DESCRIPTION_VALID, lastPostId);

        assertions.assertStatusCode200(editPrivatePost.statusCode());
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_UserDeletesPrivatePost_expect_LastPrivatePostIsDeleted() {
        Response lastPrivatePostDeleted = apiMethods.getLastPost(lastPostId);

        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

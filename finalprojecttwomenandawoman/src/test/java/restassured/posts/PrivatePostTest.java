package restassured.posts;

import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostTest extends BaseTestSetupBeforeAfter {

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
        Response createNewInvalidPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_INVALID);
        var registrationErrorModel = createNewInvalidPrivatePost.as(ErrorModel.class);
        assertions.assertStatusCode400(createNewInvalidPrivatePost.statusCode());
        assertions.assertBadRequest(registrationErrorModel);
        assertions.assertInvalidPostContent(createNewInvalidPrivatePost);
    }

    @Test
    //FTP1-46 [Edit Post] Edit existing private post
    public void when_userEditPrivatePost_expect_postIsEdited() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response editPrivatePost = posts.editPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, EDIT_POST_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(editPrivatePost.statusCode());
    }

    @Test
    public void when_userDeletePost_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

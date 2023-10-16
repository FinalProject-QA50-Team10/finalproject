package restassured.posts;

import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostPostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    public void teardown() {
        deletePublicPost();
    }

    @Test
    //FTP1-35 [Add New Post] Generate new invalid public post
    public void when_userCreateInvalidPublicPost_expect_invalidPublicPostNotBeCreated() {
        Response createNewInvalidPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_INVALID);
        var registrationErrorModel = createNewInvalidPublicPost.as(ErrorModel.class);

        assertions.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        assertions.assertBadRequest(registrationErrorModel);
        assertions.assertInvalidPostContent(createNewInvalidPublicPost);
    }

    @Test
    //FTP1-45 [Edit Post] Edit existing public post
    public void when_userEditPublicPost_expect_postIsEdited() {
        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = apiMethods.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        assertions.assertStatusCode302(signInResponse.statusCode());

        Response editPublicPost = apiMethods.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                EDIT_POST_DESCRIPTION_VALID, lastPostId);

        assertions.assertStatusCode200(editPublicPost.statusCode());
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = apiMethods.getLastPost(lastPostId);

        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

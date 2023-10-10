package restassured.base;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class BaseTestSetupBeforeAfter {

    protected final BaseSetupMethods posts = new BaseSetupMethods();
    protected final ApiTestAssertions assertions = new ApiTestAssertions();
    protected static int lastPostId;

    public void createPublicPost() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    public void createPrivatePost() {
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    public void deletePublicPost() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    public void deletePrivatePost() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePrivatePost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }
}

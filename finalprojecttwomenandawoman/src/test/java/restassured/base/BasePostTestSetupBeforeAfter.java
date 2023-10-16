package restassured.base;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class BasePostTestSetupBeforeAfter {

    protected final BaseSetupMethods apiMethods = new BaseSetupMethods();
    protected final ApiTestAssertions assertions = new ApiTestAssertions();
    protected static int lastPostId;

    public void createPublicPost() {
        //FPT1-25 [Add New Post] Create Public Post
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);

        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);

        int postId = createNewPublicPost.jsonPath().getInt("postId");

        assertions.assertPostIdIsPositive(postId);

        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    public void createPrivatePost() {
        //FPT1-26 [Add New Post] Create Private Post
        Response createNewPrivatePost = apiMethods.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);

        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);

        int postId = createNewPrivatePost.jsonPath().getInt("postId");

        assertions.assertPostIdIsPositive(postId);

        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    public void deletePublicPost() {
        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = apiMethods.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-55 [Delete Post] Delete an Existing Public Post
        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    public void deletePrivatePost() {
        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = apiMethods.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);

        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-56 [Delete Post] Delete an Existing Private Post
        Response deletePrivatePost = apiMethods.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);

        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }
}

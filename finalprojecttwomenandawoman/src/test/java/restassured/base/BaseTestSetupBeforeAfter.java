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
        //FPT1-25 [Add New Post] Create Public Post
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(createNewPublicPost.statusCode());

        // Assert the post content is valid
        assertions.assertPostContent("Valid Post");

        // Assert the post is public
        assertions.assertPostIsPublic(createNewPublicPost);

        int postId = createNewPublicPost.jsonPath().getInt("postId");

        // Assert the post ID is positive
        assertions.assertPostIdIsPositive(postId);

        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    public void createPrivatePost() {
        //FPT1-26 [Add New Post] Create Private Post
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD,
                POST_DESCRIPTION_VALID);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());

        // Assert the post content is valid
        assertions.assertPostContent("Valid Post");

        // Assert the post is not public
        assertions.assertPostIsNotPublic(createNewPrivatePost);

        int postId = createNewPrivatePost.jsonPath().getInt("postId");

        // Assert the post ID is positive
        assertions.assertPostIdIsPositive(postId);

        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    public void deletePublicPost() {
        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-55 [Delete Post] Delete an Existing Public Post
        Response deletePublicPost = posts.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(deletePublicPost.statusCode());

        // Assert that the response body is empty
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    public void deletePrivatePost() {
        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-56 [Delete Post] Delete an Existing Private Post
        Response deletePrivatePost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(deletePrivatePost.statusCode());

        // Assert that the response body is empty
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }
}

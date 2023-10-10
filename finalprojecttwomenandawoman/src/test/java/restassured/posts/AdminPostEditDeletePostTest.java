package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminPostEditDeletePostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    public void deletePost() {
        //FPT1-224 [Login Form] Login with a valid username and a valid password as an admin user
        Response signInResponse = posts.signInUser(ADMIN_NAME, ADMIN_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-61 [Delete Post] Delete an Existing Post as Admin
        Response deletePublicPost = posts.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(deletePublicPost.statusCode());

        // Assert that the response body is empty
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-60 [Edit Post] Edit existing public post as Admin
    public void when_adminEditsPost_expect_postIsEdited() {
        Response editPostAsAdmin = posts.editPublicPost(ADMIN_NAME, ADMIN_PASSWORD, EDIT_POST_AS_ADMIN, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(editPostAsAdmin.statusCode());
    }

    @Test
    //FPT1-61 [Delete Post] Delete an Existing Post as Admin
    public void when_adminUserDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

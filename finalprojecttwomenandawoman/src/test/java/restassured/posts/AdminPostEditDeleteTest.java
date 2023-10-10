package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminPostEditDeleteTest extends BaseTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    //FPT1-61 [Delete Post] Delete an Existing Post as Admin
    public void deletePost() {
        Response signInResponse = posts.signInUser(ADMIN_NAME, ADMIN_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-60 [Edit Post]  Edit existing public post as Admin
    public void when_adminEditsPost_expect_postIsEdited() {
        Response editPostAsAdmin = posts.editPublicPost(ADMIN_NAME, ADMIN_PASSWORD, EDIT_POST_AS_ADMIN, lastPostId);
        assertions.assertStatusCode200(editPostAsAdmin.statusCode());
    }

    @Test
    public void when_adminUserDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

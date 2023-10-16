package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminEditDeletePostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    public void deletePostAsAdmin() {
        //FPT1-224 [Login Form] Login with a valid username and a valid password as an admin user
        Response signInResponse = apiMethods.signInUser(ADMIN_NAME, ADMIN_PASSWORD);

        assertions.assertStatusCode302(signInResponse.statusCode());

        //FPT1-61 [Delete Post] Delete an Existing Post as Admin
        Response deletePublicPost = apiMethods.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);

        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-60 [Edit Post] Edit existing public post as Admin
    public void when_AdminEditsPost_expect_PostIsEdited() {
        Response editPostAsAdmin = apiMethods.editPublicPost(ADMIN_NAME, ADMIN_PASSWORD, EDIT_POST_AS_ADMIN,
                lastPostId);

        assertions.assertStatusCode200(editPostAsAdmin.statusCode());
    }

    @Test
    //FPT1-61 [Delete Post] Delete an Existing Post as Admin
    public void when_AdminUserDeletePost_expect_LastPublicPostDeleted() {
        Response lastPublicPostDeleted = apiMethods.getLastPost(lastPostId);

        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

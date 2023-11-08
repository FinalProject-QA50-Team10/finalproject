package restassured.posts;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminEditDeletePostTest extends BasePostTestSetupBeforeAfter {

    @Test
    @Description("FPT1-61 [Delete Post] Delete an Existing Post as Admin")
    public void when_AdminDeletesPost_expect_PostIsDeleted() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response signInResponse = apiMethods.signInUser(ADMIN_NAME, ADMIN_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    @Description("FPT1-60 [Edit Post] Edit existing public post as Admin")
    public void when_AdminEditsPost_expect_PostIsEdited() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response editPostAsAdmin = apiMethods.editPublicPost(ADMIN_NAME, ADMIN_PASSWORD, EDIT_POST_AS_ADMIN,
                lastPostId);
        assertions.assertStatusCode200(editPostAsAdmin.statusCode());

        Response deletePublicPost = apiMethods.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

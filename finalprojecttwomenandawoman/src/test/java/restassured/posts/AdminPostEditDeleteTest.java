package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminPostEditDeleteTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @BeforeEach
    //FPT1-25 [Add New Post] Generate new valid public post
    public void createPost() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
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

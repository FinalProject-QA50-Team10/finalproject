package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @BeforeEach
    //FPT1-25 [Add New Post] Generate new valid public post
    public void createPost() {
        Response createNewPublicPost = posts.createPublicPost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @AfterEach
    //FPT1-55 [Delete Post] Delete the latest public post
    public void deletePost() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
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

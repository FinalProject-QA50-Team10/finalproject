package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostTest {

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
    //FPT1-55 [Delete Post] Delete the latest public post
    public void deletePost() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FTP1-35 [Add New Post] Generate new invalid public post
    //Check it for before each
    public void when_userCreateInvalidPublicPost_expect_invalidPublicPostNotBeCreated() {
        Response createNewInvalidPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_INVALID);
        var registrationErrorModel = createNewInvalidPublicPost.as(ErrorModel.class);
        assertions.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        assertions.assertBadRequest(registrationErrorModel);
        assertions.assertInvalidPostContent(createNewInvalidPublicPost);
    }

    @Test
    //FTP1-45 [Edit Post] Edit existing public post
    public void when_userEditPublicPost_expect_postIsEdited() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response editPublicPost = posts.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, EDIT_POST_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(editPublicPost.statusCode());
    }

    @Test
    public void when_userDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }

}

package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostLikeTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @BeforeEach
    //FPT1-26 [Add New Post] Generate new valid private post
    public void createPost() {
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    @AfterEach
    //FPT1-56 [Delete Post] Delete the latest private post
    public void deletePost() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePrivatePost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    //FPT1-123 [Like] Like a private post of another user
    //FTP1-124 [Like] Dislike a private post of another user
    public void when_userLikePrivatePost_expected_likeAnotherUserPrivatePost() {
        Response signInWithUserGeorgeBush = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response likePrivatePost = posts.likePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePrivatePost.statusCode());
        assertions.assertPostIsLiked(likePrivatePost);
        Response dislikePrivatePost = posts.dislikePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePrivatePost.statusCode());
        assertions.assertPostIsDisliked(dislikePrivatePost);
    }

    @Test
    public void when_userDeletePost_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }

}

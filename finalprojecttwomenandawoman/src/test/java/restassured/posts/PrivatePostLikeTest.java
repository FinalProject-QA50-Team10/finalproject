package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_USERNAME;

public class PrivatePostLikeTest extends BaseTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPrivatePost();
    }

    @AfterEach
    public void teardown() {
        deletePrivatePost();
    }

    @Test
    //FPT1-123 [Like] Like a private post of another user
    //FTP1-124 [Like] Dislike a private post of another user
    public void when_userLikePrivatePost_expected_likeAnotherUserPrivatePost() {
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());
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

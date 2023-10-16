package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_USERNAME;

public class PrivatePostLikePostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPrivatePost();
    }

    @AfterEach
    public void teardown() {
        deletePrivatePost();
    }

    @Test
    public void when_userLikePrivatePost_expected_likeAnotherUserPrivatePost() {

        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInWithUserMrBeast = apiMethods.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());

        //FPT1-123 [Like] Verify posts Like button
        Response likePrivatePost = apiMethods.likePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        assertions.assertStatusCode200(likePrivatePost.statusCode());
        assertions.assertPostIsLiked(likePrivatePost);

        //FPT1-124 [Like] Verify posts Dislike button
        Response dislikePrivatePost = apiMethods.dislikePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        assertions.assertStatusCode200(dislikePrivatePost.statusCode());
        assertions.assertPostIsDisliked(dislikePrivatePost);
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_userDeletePost_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = apiMethods.getLastPost(lastPostId);

        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

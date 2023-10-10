package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

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
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());

        //FPT1-123 [Like] Verify posts Like button
        Response likePrivatePost = posts.likePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(likePrivatePost.statusCode());

        // Assert that the post is liked
        assertions.assertPostIsLiked(likePrivatePost);

        //FPT1-124 [Like] Verify posts Dislike button
        Response dislikePrivatePost = posts.dislikePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(dislikePrivatePost.statusCode());

        // Assert that the post is disliked
        assertions.assertPostIsDisliked(dislikePrivatePost);
    }

    @Test
    //FPT1-56 [Delete Post] Delete an Existing Private Post
    public void when_userDeletePost_expect_lastPrivatePostDeleted() {
        Response lastPrivatePostDeleted = posts.getLastPost(lastPostId);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(lastPrivatePostDeleted.statusCode());
    }
}

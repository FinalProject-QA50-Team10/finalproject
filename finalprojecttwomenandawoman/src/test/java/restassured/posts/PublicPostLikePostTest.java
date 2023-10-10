package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostLikePostTest extends BasePostTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    public void teardown() {
        deletePublicPost();
    }

    @Test
    public void when_userLikeAndDislikePublicPost_expected_likeAndDislikeAnotherUserPublicPost() {

        //FPT1-85 [Login Page] Login with valid username and valid password
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirect)
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());

        //FPT1-123 [Like] Verify posts Like button
        Response likePublicPost = posts.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(likePublicPost.statusCode());

        // Assert that the post is liked
        assertions.assertPostIsLiked(likePublicPost);

        //FPT1-124 [Like] Verify posts Dislike button
        Response dislikePublicPost = posts.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(dislikePublicPost.statusCode());

        // Assert that the post is disliked
        assertions.assertPostIsDisliked(dislikePublicPost);
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}
package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.GEORGE_BUSH_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.GEORGE_BUSH_USERNAME;

public class PublicPostLikeTest extends BaseTestSetupBeforeAfter {

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
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());

        //FPT1-123 [Like] Verify posts Like button
        Response likePublicPost = posts.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePublicPost.statusCode());
        assertions.assertPostIsLiked(likePublicPost);

        //FTP1-124 [Like] Verify posts Dislike button
        Response dislikePublicPost = posts.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePublicPost.statusCode());
        assertions.assertPostIsDisliked(dislikePublicPost);
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

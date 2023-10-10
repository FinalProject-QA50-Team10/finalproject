package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicPostLikeTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userCreatePublicPost_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }


    @Test
    @Order(2)
    //FPT1-123 [Like] Like a public post of another user
    public void when_userLikePublicPost_expected_likeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response likePublicPost = posts.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePublicPost.statusCode());
        assertions.assertPostIsLiked(likePublicPost);
    }

    @Test
    @Order(3)
    //FTP1-124 [Like] Dislike a public post of another user
    public void when_userDislikePublicPost_expected_dislikeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response dislikePublicPost = posts.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePublicPost.statusCode());
        assertions.assertPostIsDisliked(dislikePublicPost);
    }

    @Test
    @Order(4)
    //FPT1-55 [Delete Post] Delete the latest public post
    public void when_userDeletePublicPost_expected_deleteLatestPublicPost() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    @Order(5)
    public void when_userDeletePost_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPost(lastPostId);
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

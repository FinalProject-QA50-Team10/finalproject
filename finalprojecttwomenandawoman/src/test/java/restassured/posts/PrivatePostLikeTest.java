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
public class PrivatePostLikeTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Sign in with username GeorgeBush
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-26 [Add New Post] Generate new private post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent(createNewPrivatePost, "Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Sign in with username MrBeast
    public void when_anotherUserSignsIn_expect_loginSuccessful() {
        Response signInWithUserGeorgeBush = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-123 [Like] Like a private post of another user
    public void when_userSignsIn_expected_likeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response likePrivatePost = posts.likePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePrivatePost.statusCode());
        assertions.assertPostIsLiked(likePrivatePost);
    }

    @Test
    @Order(5)
    //FTP1-124 [Like] Dislike a private post of another user
    public void when_userSignsIn_expected_dislikeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response dislikePrivatePost = posts.dislikePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePrivatePost.statusCode());
        assertions.assertPostIsDisliked(dislikePrivatePost);
    }

    @Test
    @Order(6)
    //FPT1-56 [Delete Post] Delete the latest private post
    public void when_userSignsIn_expected_deleteLatestPublicPost() {
        Response signInResponse = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());
        Response deletePrivatePost = posts.deletePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    @Order(7)
    public void when_userSignIn_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPublicPost(lastPostId);
        assertions.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }

}
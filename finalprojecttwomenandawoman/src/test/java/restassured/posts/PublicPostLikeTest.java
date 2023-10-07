package restassured.posts;

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
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Sign in with username MrBeast
    public void when_userSignsIn_expected_loginSuccessfulMrBeast() {
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCode200(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Sign in with username GeorgeBush
    public void when_userSignsIn_expected_loginSuccessfulGeorgeBush() {
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        posts.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-123 [Like] Like a public post of another user
    public void when_userSignsIn_expected_likeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        posts.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response likePublicPost = posts.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        posts.assertStatusCode200(likePublicPost.statusCode());
        posts.assertPostIsLiked(likePublicPost);
    }

    @Test
    @Order(5)
    //FTP1-124 [Like] Dislike a public post of another user
    public void when_userSignsIn_expected_dislikeAnotherUserPublicPost() {
        Response signInWithUserGeorgeBush = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        posts.assertStatusCode302(signInWithUserGeorgeBush.statusCode());
        Response dislikePublicPost = posts.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        posts.assertStatusCode200(dislikePublicPost.statusCode());
        posts.assertPostIsDisliked(dislikePublicPost);
    }

    @Test
    @Order(6)
    //FPT1-55 [Delete Post] Delete the latest public post
    public void when_userSignsIn_expected_deleteLatestPublicPost() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        posts.assertStatusCode200(deletePublicPost.statusCode());
        posts.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    @Order(7)
    public void when_userSignIn_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPublicPost(lastPostId);
        posts.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }
}

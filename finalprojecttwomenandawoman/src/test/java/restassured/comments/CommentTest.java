package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();

    private Response signInWithUserJackNicholson;
    private Response signInWithUserTomCruise;

    private Response createNewPublicPost;

    private int lastPostId;

    @Test
    public void when_userSignsIn_expected_loginSuccessful() {
        signInWithUserJackNicholson = posts.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    public void when_userSignsIn_expected_newPublicPostCreated() {
        createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
        System.out.println("Last Post ID: " + lastPostId);
    }

    @Test
    public void when_anotherUserSignsIn_expected_loginSuccessful() {
        signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }
    @Test
    public void when_TomCruiseCreatesComment_expected_CommentIsCreated() {
        createNewPublicPost = posts.createPublicPost(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, POST_DESCRIPTION_VALID);

        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
        System.out.println("Last Post ID: " + lastPostId);

        Response createCommentResponse = posts.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);

        posts.assertStatusCodeIsOk(createCommentResponse.statusCode());
    }

}




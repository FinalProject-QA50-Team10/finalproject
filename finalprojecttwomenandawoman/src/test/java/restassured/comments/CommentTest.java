package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(OrderAnnotation.class)
public class CommentTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private static int lastPostId;

    @Test
    @Order(1)
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserJackNicholson = posts.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    @Order(2)
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    public void when_anotherUserSignsIn_expected_loginSuccessful() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }

    @Test
    @Order(4)
    public void when_TomCruiseCreatesComment_expected_CommentIsCreated() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
        Response createCommentResponse = posts.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);
        posts.assertStatusCodeIsOk(createCommentResponse.statusCode());
        //add more assertions
    }
}

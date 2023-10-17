package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.JACK_NICHOLSON_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.JACK_NICHOLSON_USERNAME;

public class CommentLikeTest extends BaseCommentTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        when_UserCreatesValidPublicPost_expect_ValidPublicPostIsCreated();
        createCommentAsRegisteredUser();
    }

    @AfterEach
    public void teardown() {
        deleteCommentAsRegisteredUser();
        when_UserDeletesPublicPost_expect_PublicPostIsDeleted();
    }

    @Test
    public void when_UserLikesAndDislikesComment_expect_CommentIsLikedAndDisliked() {
        //FPT1-125 [Like] Verify comments Like button
        Response signInWithUserJackNicholson = apiMethods.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);

        assertions.assertStatusCode302(signInWithUserJackNicholson.statusCode());

        Response likeCommentResponse = apiMethods.likeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD,
                lastCommentId);

        assertions.assertStatusCode200(likeCommentResponse.statusCode());
        assertions.assertCommentIsLiked(likeCommentResponse);

        //FPT1-126 [Like] Verify comments Dislike button
        Response dislikeCommentResponse = apiMethods.dislikeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD,
                lastCommentId);

        assertions.assertStatusCode200(dislikeCommentResponse.statusCode());
        assertions.assertCommentIsDisliked(dislikeCommentResponse);
    }
}

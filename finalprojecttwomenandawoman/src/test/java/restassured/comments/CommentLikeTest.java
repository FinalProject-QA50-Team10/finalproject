package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentLikeTest extends BaseCommentTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
        createComment();
    }

    @AfterEach
    public void teardown() {
        deleteComment();
        deletePublicPost();
    }

    @Test
    public void when_userLikesComment_expect_commentIsLiked() {
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

package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentLikeTest extends BaseCommentTestSetupBeforeAfter {

    @Test
    //FPT1-125 [Like] Verify comments Like button
    public void when_UserLikesComment_expect_CommentIsLiked() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response createCommentResponse = apiMethods.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                COMMENT_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(createCommentResponse.statusCode());
        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");

        Response likeCommentResponse = apiMethods.likeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD,
                lastCommentId);
        assertions.assertStatusCode200(likeCommentResponse.statusCode());
        assertions.assertCommentIsLiked(likeCommentResponse);

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-126 [Like] Verify comments Dislike button
    public void when_UserDislikesComment_expect_CommentIsDisliked() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response createCommentResponse = apiMethods.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                COMMENT_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(createCommentResponse.statusCode());
        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");

        Response likeCommentResponse = apiMethods.likeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD,
                lastCommentId);
        assertions.assertStatusCode200(likeCommentResponse.statusCode());
        assertions.assertCommentIsLiked(likeCommentResponse);

        Response dislikeCommentResponse = apiMethods.dislikeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD,
                lastCommentId);
        assertions.assertStatusCode200(dislikeCommentResponse.statusCode());
        assertions.assertCommentIsDisliked(dislikeCommentResponse);

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

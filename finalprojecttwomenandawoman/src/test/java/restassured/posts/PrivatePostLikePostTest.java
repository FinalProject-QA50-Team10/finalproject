package restassured.posts;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PrivatePostLikePostTest extends BasePostTestSetupBeforeAfter {

    @Test
    @Description("FPT1-123 [Like] Verify posts Like button")
    public void when_UserLikesPrivatePost_expect_PrivatePostIsLiked() {
        Response createNewPrivatePost = apiMethods.createPrivatePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");

        Response likePrivatePost = apiMethods.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePrivatePost.statusCode());
        assertions.assertPostIsLiked(likePrivatePost);

        Response deletePrivatePost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

    @Test
    @Description("FPT1-124 [Like] Verify posts Dislike button")
    public void when_UserDislikesPrivatePost_expect_PrivatePostIsDisliked() {
        Response createNewPrivatePost = apiMethods.createPrivatePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPrivatePost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsNotPublic(createNewPrivatePost);
        int postId = createNewPrivatePost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");

        Response likePrivatePost = apiMethods.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePrivatePost.statusCode());
        assertions.assertPostIsLiked(likePrivatePost);

        Response dislikePrivatePost = apiMethods.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePrivatePost.statusCode());
        assertions.assertPostIsDisliked(dislikePrivatePost);

        Response deletePrivatePost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePrivatePost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePrivatePost);
    }

}

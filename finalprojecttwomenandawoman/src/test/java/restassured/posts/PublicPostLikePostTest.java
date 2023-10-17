package restassured.posts;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostLikePostTest extends BasePostTestSetupBeforeAfter {

    @Test
    //FPT1-123 [Like] Verify posts Like button
    public void when_UserLikesPublicPost_expect_PublicPostIsLiked() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response likePublicPost = apiMethods.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePublicPost.statusCode());
        assertions.assertPostIsLiked(likePublicPost);

        Response signInResponse = apiMethods.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);

    }

    @Test
    //FPT1-124 [Like] Verify posts Dislike button
    public void when_UserDislikesPublicPost_expect_PublicPostIsDisliked() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response likePublicPost = apiMethods.likePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(likePublicPost.statusCode());
        assertions.assertPostIsLiked(likePublicPost);

        Response dislikePublicPost = apiMethods.dislikePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, lastPostId);
        assertions.assertStatusCode200(dislikePublicPost.statusCode());
        assertions.assertPostIsDisliked(dislikePublicPost);

        Response signInResponse = apiMethods.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);

    }

}

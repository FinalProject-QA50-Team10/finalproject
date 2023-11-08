package restassured.comments;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentInvalidCreationTest extends BaseCommentTestSetupBeforeAfter {

    @Test
    @Description("FPT1-170 [Comment] Create Comment With 1001 Characters as Registered User")
    public void when_UserCreatesInvalidComment_expect_ErrorStatusCode() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        String invalidComment = apiMethods.generateInvalidComment();
        Response signInWithUserTomCruise = apiMethods.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = apiMethods.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                invalidComment, lastPostId);
        assertions.assertStatusCode400(createCommentResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

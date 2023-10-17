package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentManipulationTest extends BaseCommentTestSetupBeforeAfter {

    @Test
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void when_UserCreatesComment_expect_CommentIsCreated() {
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

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-178 [Comment] Edit Comment Successfully as Registered User
    public void when_UserEditsComment_expect_CommentIsEdited() {
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

        Response editCommentResponse = apiMethods.editComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                EDIT_COMMENT_CONTENT,
                lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-182 [Comment] Delete Comment Successfully as Registered User
    public void when_UserDeletesComment_expect_CommentIsDeleted() {
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

        Response editCommentResponse = apiMethods.editComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                EDIT_COMMENT_CONTENT,
                lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

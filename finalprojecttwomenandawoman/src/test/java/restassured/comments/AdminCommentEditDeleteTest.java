package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminCommentEditDeleteTest extends BaseCommentTestSetupBeforeAfter {

    @Test
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_AdminEditsComment_expect_CommentIsEdited() {
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

        Response editCommentResponse = apiMethods.editComment(ADMIN_NAME, ADMIN_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_AdminDeletesComment_expect_CommentIsDeleted() {
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

        Response deleteCommentResponse = apiMethods.deleteComment(ADMIN_NAME, ADMIN_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

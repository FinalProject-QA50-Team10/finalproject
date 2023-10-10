package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminCommentEditDeleteTest extends BaseCommentTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
        createComment();
    }

    @AfterEach
    public void teardown() {
        deletePublicPost();
    }

    @Test
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_adminEditsComment_expect_commentIsEdited() {
        Response editCommentResponse = apiMethods.editComment(ADMIN_NAME, ADMIN_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());
    }

    @Test
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_adminDeletesComment_expect_commentIsDeleted() {
        Response deleteCommentResponse = apiMethods.deleteComment(ADMIN_NAME, ADMIN_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);
    }
}

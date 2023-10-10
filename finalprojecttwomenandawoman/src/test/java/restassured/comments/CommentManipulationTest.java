package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentManipulationTest extends BaseCommentTestSetupBeforeAfter {

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
    //FPT1-178 [Comment] Edit Comment Successfully as Registered User
    public void when_userEditsComment_expect_commentIsEdited() {
        Response editCommentResponse = apiMethods.editComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                EDIT_COMMENT_CONTENT,
                lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());
    }
}

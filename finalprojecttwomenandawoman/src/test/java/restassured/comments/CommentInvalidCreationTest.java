package restassured.comments;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restassured.base.BaseCommentTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.TOM_CRUISE_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.TOM_CRUISE_USERNAME;

public class CommentInvalidCreationTest extends BaseCommentTestSetupBeforeAfter {

    @BeforeEach
    public void setup() {
        createPublicPost();
    }

    @AfterEach
    public void teardown() {
        deletePublicPost();
    }

    @Test
    //FPT1-170 [Comment] Create Comment With 1001 Characters as Registered User
    public void when_UserCreatesInvalidComment_expect_ErrorStatusCode() {
        String invalidComment = apiMethods.generateInvalidComment();

        Response signInWithUserTomCruise = apiMethods.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);

        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = apiMethods.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                invalidComment, lastPostId);

        assertions.assertStatusCode400(createCommentResponse.statusCode());
    }
}

package restassured.base;

import io.restassured.response.Response;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class BaseCommentTestSetupBeforeAfter extends BasePostTestSetupBeforeAfter {

    protected static int lastCommentId;

    public void createComment() {
        //FPT1-167 [Comment] Create Comment Successfully as Registered User
        Response signInWithUserTomCruise = apiMethods.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);

        // Assert that the HTTP status code is 302 (Found/Redirected)
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = apiMethods.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                COMMENT_DESCRIPTION_VALID, lastPostId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(createCommentResponse.statusCode());

        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");
    }

    public void deleteComment() {
        //FPT1-182 [Comment] Delete Comment Successfully as Registered User
        Response deleteCommentResponse = apiMethods.deleteComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD,
                lastCommentId);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());

        // Assert that the response body is empty
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);
    }
}

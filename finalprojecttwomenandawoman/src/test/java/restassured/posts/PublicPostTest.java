package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(OrderAnnotation.class)
public class PublicPostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private static int lastPostId;

    @Test
    @Order(1)
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    public void when_userSignsIn_expect_invalidPublicPostNotBeCreated() {
        Response createNewInvalidPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_INVALID);
        posts.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        posts.assertBadRequestError(createNewInvalidPublicPost);
    }

    @Test
    @Order(4)
    public void when_userSignsIn_expect_editPublicPostCreated() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInResponse.statusCode());
        Response editPublicPost = posts.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, EDIT_POST_DESCRIPTION_VALID, lastPostId);
        posts.assertStatusCodeIsOk(editPublicPost.statusCode());
    }

    @Test
    @Order(5)
    public void when_userSignIn_expect_deletePublicPostCreated() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        posts.assertStatusCodeIsOk(deletePublicPost.statusCode());
        posts.assertResponseBodyIsEmpty(deletePublicPost);

    }

    @Test
    @Order(6)
    public void when_userSignIn_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPublicPost(lastPostId);
        posts.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }

}

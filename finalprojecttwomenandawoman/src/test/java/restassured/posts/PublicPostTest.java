package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.RegistrationErrorModel;
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
    //FPT1-85 [Login Page] Sign in with username MrBeast
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCode200(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FTP1-35 [Add New Post] Generate new invalid public post
    public void when_userSignsIn_expect_invalidPublicPostNotBeCreated() {
        Response createNewInvalidPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_INVALID);
        var t = createNewInvalidPublicPost.as(RegistrationErrorModel.class);
        posts.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        posts.assertBadRequest(t);
    }

    @Test
    @Order(4)
    //FTP1-45 [Edit Post] Edit existing public post
    public void when_userSignsIn_expect_editPublicPostCreated() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInResponse.statusCode());
        Response editPublicPost = posts.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, EDIT_POST_DESCRIPTION_VALID, lastPostId);
        posts.assertStatusCode200(editPublicPost.statusCode());
    }

    @Test
    @Order(5)
    //FPT1-55 [Delete Post] Confirming user can delete his own public post
    public void when_userSignIn_expect_deletePublicPostCreated() {
        Response signInResponse = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInResponse.statusCode());
        Response deletePublicPost = posts.deletePublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        posts.assertStatusCode200(deletePublicPost.statusCode());
        posts.assertResponseBodyIsEmpty(deletePublicPost);

    }

    @Test
    @Order(6)
    public void when_userSignIn_expect_lastPublicPostDeleted() {
        Response lastPublicPostDeleted = posts.getLastPublicPost(lastPostId);
        posts.assertStatusCode404(lastPublicPostDeleted.statusCode());
    }

}

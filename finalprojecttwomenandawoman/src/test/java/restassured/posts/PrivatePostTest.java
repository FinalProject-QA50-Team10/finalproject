package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.RegistrationErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrivatePostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Sign in with username GeorgeBush
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD);
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-26 [Add New Post] Generate new valid private post
    public void when_userSignsIn_expected_newPrivatePostCreated() {
        Response createNewPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCode200(createNewPrivatePost.statusCode());
        posts.assertPostContent(createNewPrivatePost, "Valid Post");
        posts.assertPostIsNotPublic(createNewPrivatePost);
        lastPostId = createNewPrivatePost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    public void when_userSignsIn_expect_invalidPrivatePostNotBeCreated() {
        Response createNewInvalidPrivatePost = posts.createPrivatePost(GEORGE_BUSH_USERNAME, GEORGE_BUSH_PASSWORD, POST_DESCRIPTION_INVALID);
        var registrationErrorModel = createNewInvalidPrivatePost.as(RegistrationErrorModel.class);
        posts.assertStatusCode400(createNewInvalidPrivatePost.statusCode());
        posts.assertBadRequest(registrationErrorModel);
        posts.assertInvalidPostContent(createNewInvalidPrivatePost);
    }
}

package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.MR_BEAST_USERNAME;

public class PublicPostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();

    private Response signInWithUserMrBeast;

    private Response createNewPublicPost;

    private Response createNewInvalidPublicPost;

    private Response editPublicPost;

    private static int lastPostId;

    @BeforeEach
    public void loginSuccessful() {

        signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());

    }

    @Test
    public void when_userSignsIn_expected_newPublicPostCreated() {

        createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
        System.out.println("Last Post ID: " + lastPostId);
    }

    @Test
    public void when_userSignsIn_expect_invalidPublicPostNotBeCreated() {
        createNewInvalidPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_INVALID);
        posts.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        posts.assertBadRequestError(createNewInvalidPublicPost);
    }

    @Test
    public void when_userSignsIn_expect_editPublicPostCreated() {
        editPublicPost = posts.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, EDIT_POST_DESCRIPTION_VALID,
                lastPostId);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
    }
}
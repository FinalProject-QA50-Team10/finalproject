package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();

    private Response signInWithUserMrBeast;

    private Response createNewPublicPost;

    @BeforeEach
    public void when_userSignsIn_as_MrBeast_expected_loginSuccessful() {

        signInWithUserMrBeast = posts.signInWithUserMrBeast();
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());

    }

    @Test
    public void when_userSignsIn_as_MrBeast_expected_create_new_public_post() {

        createNewPublicPost = posts.crp(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());

    }
}
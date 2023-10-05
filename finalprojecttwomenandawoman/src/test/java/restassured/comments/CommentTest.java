package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();

    private Response signInWithUserJackNicholson;

    private Response createNewPublicPost;

    @Test
    public void when_userSignsIn_expected_loginSuccessful() {
        signInWithUserJackNicholson = posts.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    public void when_userSignsIn_expected_newPublicPostCreated() {
        createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCodeIsOk(createNewPublicPost.statusCode());
    }

}




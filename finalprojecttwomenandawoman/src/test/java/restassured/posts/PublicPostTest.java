package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class PublicPostTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();

    private Response signInWithUserMrBeast;

    @Test
    public void when_userSignsIn_as_MrBeast_expected_loginSuccessful() {

        signInWithUserMrBeast = posts.signInWithUserMrBeast();
        posts.assertStatusCode302(signInWithUserMrBeast.statusCode());

    }
}
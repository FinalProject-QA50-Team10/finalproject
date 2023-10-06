package restassured.posts;

import com.telerikacademy.testframework.api.BaseSetupMethods;
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


}

package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class LoginTest {

    private final BaseSetupMethods loginAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response loginResponse;

    @Test
    @Description("FPT1-409 [Authentication] Login with valid username and valid password")
    public void when_UnauthenticatedUserLogsInWithValidCredentials_expect_SuccessfulLogin() {
        loginResponse = loginAPI.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);

        assertions.assertStatusCode302(loginResponse.statusCode());
    }

    @Test
    @Description("FPT1-410 [Authentication] Login with a valid username and an invalid password")
    public void when_UnauthenticatedUserLogsInWithValidUsernameAndInvalidPassword_expect_UnsuccessfulLogin() {
        loginResponse = loginAPI.signInUser(MR_BEAST_USERNAME, GEORGE_BUSH_PASSWORD);

        assertions.assertStatusCode302(loginResponse.statusCode());
    }
}

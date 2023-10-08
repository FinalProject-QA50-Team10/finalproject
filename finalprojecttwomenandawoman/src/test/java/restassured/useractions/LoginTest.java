package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class LoginTest {

    private final BaseSetupMethods loginAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response loginResponse;

    @Test
    public void when_unauthenticatedUserLoginWithValidCredentials_expect_successLoginUser() {
        loginResponse = loginAPI.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(loginResponse.statusCode());
    }

    //----------------Kakvi da sa assurtite

    @Test
    public void when_unauthenticatedUserLoginWithValidUsernameAndInvalidPassword_expect_successLoginUser() {
        loginResponse = loginAPI.signInUser(MR_BEAST_USERNAME, GEORGE_BUSH_PASSWORD);
        assertions.assertStatusCode302(loginResponse.statusCode());
    }
}

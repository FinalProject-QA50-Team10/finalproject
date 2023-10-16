package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class RegistrationTest {
    private final BaseSetupMethods registrationAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response registerResponse;

    @Test
    //FPT1-135 [Registration] Successful Registration
    public void when_UnregisteredUserRegistersWithValidData_expect_SuccessfulRegistration() {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, RANDOM_USERNAME);

        assertions.assertRegistrationMessage(registerResponse);
    }

    @Test
    //FPT1-136 [Registration] Empty Fields
    public void when_UnregisteredUserRegistersWithEmptyData_expect_BadRequest() {
        registerResponse = registrationAPI.registerUser(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        assertions.assertStatusCode400(registerResponse.statusCode());
        assertions.assertBadRequest(errorBody);
        assertions.assertBadRequestMessage(errorBody);
    }

    @Test
    //FPT1-149 [Registration] Attempt Registration With Cyrillic Characters in Username
    public void when_UnregisteredUserRegistersWithInvalidData_expect_BadRequest() {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, INVALID_NAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        assertions.assertStatusCode400(registerResponse.statusCode());
        assertions.assertBadRequest(errorBody);
        assertions.assertBadRequestMessage(errorBody);
    }

    @Test
    //FPT1-150 [Registration] Attempt Registration With Empty Password Field
    public void when_UnregisteredUserRegistersWithEmptyPassword_expect_BadRequest() {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, EMPTY_STRING, RANDOM_EMAIL, RANDOM_USERNAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        assertions.assertStatusCode400(registerResponse.statusCode());
        assertions.assertBadRequest(errorBody);
        assertions.assertBadRequestMessage(errorBody);
    }

    @Test
    //FPT1-162 [Registration] Attempt Registration With Email Containing Spaces
    public void when_UnregisteredUserRegistersWithInvalidEmail_expect_BadRequest() {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, INVALID_EMAIL, RANDOM_USERNAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        assertions.assertStatusCode400(registerResponse.statusCode());
        assertions.assertBadRequest(errorBody);
        assertions.assertBadRequestMessage(errorBody);
    }
}

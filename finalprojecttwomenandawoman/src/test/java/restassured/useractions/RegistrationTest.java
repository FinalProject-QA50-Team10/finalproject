package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class RegistrationTest {
    private final BaseSetupMethods registrationAPI = new BaseSetupMethods();
    private Response registerResponse;

    @Test
    public void when_unregisteredUserFillRegisterFormWithValidData_expect_successRegistration()
    {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, RANDOM_USERNAME);
        registrationAPI.assertRegistrationMessage(registerResponse);
    }

    @Test
    public void when_unregisteredUserFillRegisterFormWithEmptyData_expect_badRequest()
    {
        registerResponse = registrationAPI.registerUser(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        registrationAPI.assertStatusCode400(registerResponse.statusCode());
        registrationAPI.assertBadRequest(errorBody);
        registrationAPI.assertBadRequestMessage(errorBody);
    }

    @Test
    public void when_unregisteredUserFillRegisterFormWithInvalidData_expect_badRequest()
    {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, INVALID_NAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        registrationAPI.assertStatusCode400(registerResponse.statusCode());
        registrationAPI.assertBadRequest(errorBody);
        registrationAPI.assertBadRequestMessage(errorBody);
    }

    @Test
    public void when_unregisteredUserFillRegisterFormWithEmptyPassword_expect_badRequest()
    {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, EMPTY_STRING, RANDOM_EMAIL, RANDOM_USERNAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        registrationAPI.assertStatusCode400(registerResponse.statusCode());
        registrationAPI.assertBadRequest(errorBody);
        registrationAPI.assertBadRequestMessage(errorBody);
    }

    @Test
    public void when_unregisteredUserFillRegisterFormWithInvalidEmail_expect_badRequest()
    {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, INVALID_EMAIL, RANDOM_USERNAME);
        var errorBody = registrationAPI.convertErrorBody(registerResponse);

        registrationAPI.assertStatusCode400(registerResponse.statusCode());
        registrationAPI.assertBadRequest(errorBody);
        registrationAPI.assertBadRequestMessage(errorBody);
    }
}

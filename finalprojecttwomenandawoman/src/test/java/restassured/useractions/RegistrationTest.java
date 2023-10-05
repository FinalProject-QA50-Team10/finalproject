package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.RANDOM_EMAIL;
import static com.telerikacademy.testframework.api.utils.Constants.RANDOM_USERNAME;

public class RegistrationTest {
    private final BaseSetupMethods registrationAPI = new BaseSetupMethods();
    private String registerResponse;

    @Test
    public void when_unregisteredUserFillRegisterFormWithValidData_expect_successRegistration()
    {
        registrationAPI.generateRandomUsername();
        registerResponse = registrationAPI.registerUser();
        registrationAPI.assertRegistrationMessage(registerResponse);
    }
}

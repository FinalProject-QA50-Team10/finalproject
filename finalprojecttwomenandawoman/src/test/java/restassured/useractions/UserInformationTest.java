package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UserInformationTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response userResponse;

    @Test
    public void when_getUserInformationByValidIdAndValidUsername_expected_success() {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME, TOM_CRUISE_ID);
        var userModel = userResponse.as(UserInformationModel.class);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(userResponse.statusCode());
        // Assert that the user ID matches
        assertions.assertUserId(userModel, TOM_CRUISE_ID);
        // Assert that the username matches
        assertions.assertUserUsername(userModel, TOM_CRUISE_USERNAME);
    }

    @Test
    public void when_getUserInformationByUsernameWithoutId_expected_notFound() {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME, EMPTY_INTEGER);
        var userModel = userResponse.as(ErrorModel.class);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(userResponse.statusCode());
        // Assert that the "Not Found" message is present
        assertions.assertNotFoundMessage(userModel);
        // Assert that the "Not Found" error is present
        assertions.assertNotFound(userModel);
    }

    @Test
    public void when_getUserInformationByDifferentIdAndValidUsername_expected_unauthorized() {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, TOM_CRUISE_ID);
        var userModel = userResponse.as(ErrorModel.class);

        // Assert that the HTTP status code is 401 (Unauthorized)
        assertions.assertStatusCode401(userResponse.statusCode());
        // Assert that the "Unauthorized" message is present
        assertions.assertUnauthorizedMessage(userModel);
        // Assert that the "Unauthorized" error is present
        assertions.assertUnauthorizedError(userModel);
    }

    @Test
    public void when_getUserInformationByInvalidIdAndValidUsername_expected_notFound() {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, INVALID_ID);
        var userModel = userResponse.as(ErrorModel.class);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(userResponse.statusCode());
        // Assert that the "Not Found" message is present
        assertions.assertNotFoundMessage(userModel);
        // Assert that the "Not Found" error is present
        assertions.assertNotFound(userModel);
    }
}

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
    public void when_UserFetchesInformationByValidIdAndUsername_expect_Success() {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME, TOM_CRUISE_ID);
        var userModel = userResponse.as(UserInformationModel.class);

        assertions.assertStatusCode200(userResponse.statusCode());
        assertions.assertUserId(userModel, TOM_CRUISE_ID);
        assertions.assertUserUsername(userModel, TOM_CRUISE_USERNAME);
    }

    @Test
    public void when_UserFetchesInformationByUsernameWithoutId_expect_NotFound() {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME, EMPTY_INTEGER);
        var userModel = userResponse.as(ErrorModel.class);

        assertions.assertStatusCode404(userResponse.statusCode());
        assertions.assertNotFoundMessage(userModel);
        assertions.assertNotFound(userModel);
    }

    @Test
    public void when_UserFetchesInformationByDifferentIdAndValidUsername_expected_Unauthorized() {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, TOM_CRUISE_ID);
        var userModel = userResponse.as(ErrorModel.class);

        assertions.assertStatusCode401(userResponse.statusCode());
        assertions.assertUnauthorizedMessage(userModel);
        assertions.assertUnauthorizedError(userModel);
    }

    @Test
    public void when_UserFetchesInformationByInvalidIdAndValidUsername_expected_NotFound() {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, INVALID_ID);
        var userModel = userResponse.as(ErrorModel.class);

        assertions.assertStatusCode404(userResponse.statusCode());
        assertions.assertNotFoundMessage(userModel);
        assertions.assertNotFound(userModel);
    }
}

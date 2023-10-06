package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.RegistrationErrorModel;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UserInformationTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private Response userResponse;

    @Test
    public void when_getUserInformationByValidIdAndValidUsername_expected_success()
    {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME,TOM_CRUISE_ID);
        var userModel = userResponse.as(UserInformationModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertUserId(userModel, TOM_CRUISE_ID);
        userAPI.assertUserUsername(userModel, TOM_CRUISE_USERNAME);
    }

    @Test
    public void when_getUserInformationByValidId_expected_success()
    {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME, EMPTY_INTEGER);
        var userModel = userResponse.as(RegistrationErrorModel.class);

        userAPI.assertStatusCode404(userResponse.statusCode());
        userAPI.assertNotFoundMessage(userModel);
        userAPI.assertNotFound(userModel);
    }

    @Test
    public void when_getUserInformationByDifferentIdAndValidUsername_expected_success()
    {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, TOM_CRUISE_ID);
        var userModel = userResponse.as(RegistrationErrorModel.class);

        userAPI.assertStatusCode401(userResponse.statusCode());
        userAPI.assertUnauthorizedMessage(userModel);
        userAPI.assertUnauthorizedError(userModel);
    }

    @Test
    public void when_getUserInformationByInvalidIdAndValidUsername_expected_success()
    {
        userResponse = userAPI.getUserInformation(MR_BEAST_USERNAME, INVALID_ID);
        var userModel = userResponse.as(RegistrationErrorModel.class);

        userAPI.assertStatusCode404(userResponse.statusCode());
        userAPI.assertNotFoundMessage(userModel);
        userAPI.assertNotFound(userModel);
    }
}

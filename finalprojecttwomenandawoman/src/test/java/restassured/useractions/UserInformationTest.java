package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UserInformationTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private Response userResponse;

    @Test
    public void when_getUserInformationByIdAndUsername_expected_success()
    {
        userResponse = userAPI.getUserInformation(TOM_CRUISE_USERNAME,TOM_CRUISE_ID);
        var userModel = userResponse.as(UserInformationModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertUserId(userModel, TOM_CRUISE_ID);
        userAPI.assertUserUsername(userModel, TOM_CRUISE_USERNAME);
    }

}

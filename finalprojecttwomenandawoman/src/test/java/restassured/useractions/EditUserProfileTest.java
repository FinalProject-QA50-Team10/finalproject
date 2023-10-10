package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.EditProfileModel;
import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class EditUserProfileTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response userResponse;

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisUserProfile_expect_successfullyEditedProfile() {
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                FOR_EDIT_BIRTHDAY, FOR_EDIT_FIRST_NAME, FOR_EDIT_ID, FOR_EDIT_LAST_NAME);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(userResponse.statusCode());
        // Assert that the User ID matches
        assertions.assertUserId(editedProfileModel, FOR_EDIT_ID);
        // Assert that the First Name matches
        assertions.assertFirstName(editedProfileModel, FOR_EDIT_FIRST_NAME);
        // Assert that the Last Name matches
        assertions.assertLastName(editedProfileModel, FOR_EDIT_LAST_NAME);
        // Assert that the Birth Date matches
        assertions.assertBirthDate(editedProfileModel, FOR_EDIT_BIRTHDAY);
    }

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisFirstName_expect_successfullyEditedProfile() {
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, GEORGE_BUSH_FIRST_NAME, FOR_EDIT_ID, EMPTY_STRING);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(userResponse.statusCode());
        // Assert that the First Name matches
        assertions.assertFirstName(editedProfileModel, GEORGE_BUSH_FIRST_NAME);
        // Assert that the First Name has been changed
        assertions.assertNotEqual(unchangedUser.firstName, editedProfileModel.firstName);
    }

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisLastName_expect_successfullyEditedProfile() {
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, EMPTY_STRING, FOR_EDIT_ID, GEORGE_BUSH_LAST_NAME);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(userResponse.statusCode());
        // Assert that the Last Name matches
        assertions.assertLastName(editedProfileModel, GEORGE_BUSH_LAST_NAME);
        // Assert that the Last Name has been changed
        assertions.assertNotEqual(unchangedUser.lastNAme, editedProfileModel.lastName);
    }

    @Test
    //FPT1-23 [Edit Profile] Edit User Profile with Empty Data and Verify Error Handling
    public void when_userEditHisPersonalProfileWithEmptyData_expect_errorHandling() {
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, EMPTY_STRING, EMPTY_INTEGER, EMPTY_STRING);
        var editedProfileModel = userResponse.as(ErrorModel.class);
        var userProfile = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(userResponse.statusCode());
        // Assert that the response is "Not Found"
        assertions.assertNotFound(editedProfileModel);
        // Assert that the profile has not been changed
        assertions.assertEquals(unchangedUser, userProfile);
    }
}

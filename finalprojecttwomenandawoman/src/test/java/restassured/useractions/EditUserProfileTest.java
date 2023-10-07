package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.EditProfileModel;
import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Constants.FOR_EDIT_LAST_NAME;

public class EditUserProfileTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private Response userResponse;

    @BeforeEach
    public void login(){
        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
    }

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisUserProfile_expect_successfullyEditedProfile(){
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                FOR_EDIT_BIRTHDAY, FOR_EDIT_FIRST_NAME, FOR_EDIT_ID, FOR_EDIT_LAST_NAME);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertUserId(editedProfileModel, FOR_EDIT_ID);
        userAPI.assertFirstName(editedProfileModel, FOR_EDIT_FIRST_NAME);
        userAPI.assertLastName(editedProfileModel, FOR_EDIT_LAST_NAME);
        userAPI.assertBirthDate(editedProfileModel, FOR_EDIT_BIRTHDAY);
    }

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisFirstName_expect_successfullyEditedProfile(){
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, GEORGE_BUSH_FIRST_NAME, FOR_EDIT_ID, EMPTY_STRING);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertFirstName(editedProfileModel, GEORGE_BUSH_FIRST_NAME);
        userAPI.assertNotEqual(unchangedUser.firstName, editedProfileModel.firstName);
    }

    @Test
    //FPT1-1 [Edit Profile] Edit User Profile with Personal Information
    public void when_userEditHisLastName_expect_successfullyEditedProfile(){
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, EMPTY_STRING, FOR_EDIT_ID, GEORGE_BUSH_LAST_NAME);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertLastName(editedProfileModel, GEORGE_BUSH_LAST_NAME);
        userAPI.assertNotEqual(unchangedUser.lastNAme, editedProfileModel.lastName);
    }

    @Test
    //FPT1-23 [Edit Profile] Edit User Profile with Empty Data and Verify Error Handling
    public void when_userEditHisPersonalProfileWithEmptyData_expect_errorHandling(){
        var unchangedUser = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, EMPTY_STRING, EMPTY_INTEGER, EMPTY_STRING);
        var editedProfileModel = userResponse.as(ErrorModel.class);

        userAPI.assertStatusCode404(userResponse.statusCode());
        userAPI.assertNotFound(editedProfileModel);
        userAPI.assertNotFoundMessage(editedProfileModel);
    }
}

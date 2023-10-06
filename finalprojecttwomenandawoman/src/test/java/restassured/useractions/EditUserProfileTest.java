package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.EditProfileModel;
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
    public void when_userEditHisFirstName_expect_successfullyEditedProfile(){
        userResponse = userAPI.editUserProfile(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD,
                EMPTY_STRING, GEORGE_BUSH_FIRST_NAME, FOR_EDIT_ID, EMPTY_STRING);
        var editedProfileModel = userResponse.as(EditProfileModel.class);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertFirstName(editedProfileModel, GEORGE_BUSH_FIRST_NAME);
    }
}

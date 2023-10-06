package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class SendFriendRequestTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private Response userResponse;

    @BeforeEach
    public void login(){
        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
    }

    @Test
    public void when_sendFriendRequestToAnotherUserWhoIsNotYourFriend_expect_successfulAcceptIt(){
        userAPI.generateRandomUsername();
        userResponse = userAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, RANDOM_USERNAME);
        userAPI.assertRegistrationMessage(userResponse);


    }


}

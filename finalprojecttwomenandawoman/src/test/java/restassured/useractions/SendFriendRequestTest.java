package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.UserInformationModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class SendFriendRequestTest {
    private final BaseSetupMethods userAPI = new BaseSetupMethods();
    private Response userResponse;

    @BeforeEach
    public void precondition(){
        userAPI.generateRandomUsername();
        userResponse = userAPI.registerUser(VALID_JOB_TITLE, TOM_CRUISE_PASSWORD, RANDOM_EMAIL, RANDOM_USERNAME);
        userAPI.assertRegistrationMessage(userResponse);
    }

    @Test
    public void when_sendFriendRequestToAnotherUserWhoIsNotYourFriend_expect_successfulSentRequest(){
        var userAccepting = userAPI.getUserInformation(RANDOM_USERNAME, LAST_REGISTERED_USER_ID)
                .as(UserInformationModel.class);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertSentFriendRequestMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);
    }

    @Test
    public void when_sendFriendRequestToAnotherUserWhoIsNotYourFriend_expect_successfulRequestsAreVisible(){
        var userAccepting = userAPI.getUserInformation(RANDOM_USERNAME, LAST_REGISTERED_USER_ID)
                .as(UserInformationModel.class);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertSentFriendRequestMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);

        userResponse = userAPI.signInUser(RANDOM_USERNAME, TOM_CRUISE_PASSWORD);
        userResponse = userAPI.getUserRequest(userAccepting.username, TOM_CRUISE_PASSWORD, userAccepting.id);
        var listOfRequests = userAPI.getListOfRequests(userResponse);

        userAPI.assertListIsNotEmpty(Collections.singletonList(listOfRequests));
    }

    @Test
    public void when_sendFriendRequestToAnotherUserWhoIsNotYourFriend_expect_successfulAcceptIt(){
        var userAccepting = userAPI.getUserInformation(RANDOM_USERNAME, LAST_REGISTERED_USER_ID)
                .as(UserInformationModel.class);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        var userSending = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertSentFriendRequestMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);

        userResponse = userAPI.signInUser(RANDOM_USERNAME, TOM_CRUISE_PASSWORD);
        userResponse = userAPI.getUserRequest(userAccepting.username, TOM_CRUISE_PASSWORD, userAccepting.id);
        userAPI.assertStatusCode200(userResponse.statusCode());
        var responseMessage = userAPI.approveFriendRequest(RANDOM_USERNAME, TOM_CRUISE_PASSWORD, userAccepting.id, userSending.id);
        userAPI.assertSuccessfulAcceptMessage(responseMessage);
    }

    @Test
    public void when_sendTwiceFriendRequestToAnotherUserWhoIsNotYourFriend_expect_errorMessage(){
        var userAccepting = userAPI.getUserInformation(RANDOM_USERNAME, LAST_REGISTERED_USER_ID)
                .as(UserInformationModel.class);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);
        userAPI.assertSentFriendRequestMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);
        userAPI.assertDuplicatedRequestErrorMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);
    }

    @Test
    public void when_sendFriendRequestToAnotherUserWhoIsAlreadyYourFriend_expect_errorMessage(){
        var userAccepting = userAPI.getUserInformation(RANDOM_USERNAME, LAST_REGISTERED_USER_ID)
                .as(UserInformationModel.class);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        var userSending = userAPI.getUserInformation(FOR_EDIT_USERNAME, FOR_EDIT_ID).as(UserInformationModel.class);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);

        userAPI.assertStatusCode200(userResponse.statusCode());
        userAPI.assertSentFriendRequestMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);

        userResponse = userAPI.signInUser(RANDOM_USERNAME, TOM_CRUISE_PASSWORD);
        userResponse = userAPI.getUserRequest(userAccepting.username, TOM_CRUISE_PASSWORD, userAccepting.id);
        userAPI.assertStatusCode200(userResponse.statusCode());
        var responseMessage = userAPI.approveFriendRequest(RANDOM_USERNAME, TOM_CRUISE_PASSWORD, userAccepting.id, userSending.id);
        userAPI.assertSuccessfulAcceptMessage(responseMessage);

        userResponse = userAPI.signInUser(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        userResponse = userAPI.sendFriendRequest(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD, userAccepting.id, RANDOM_USERNAME);
        userAPI.assertDuplicatedRequestErrorMessage(userResponse, FOR_EDIT_USERNAME, RANDOM_USERNAME);
    }
}

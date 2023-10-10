package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static org.apache.http.HttpStatus.*;

public class ApiTestAssertions extends BaseSetupMethods {

    public final BaseSetupMethods baseMethods = new BaseSetupMethods();

    public void assertStatusCode200(int statusCode) {
        Assertions.assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        System.out.println("Status Code is 200.");
    }

    public void assertStatusCode302(int statusCode) {
        Assertions.assertEquals(SC_MOVED_TEMPORARILY, statusCode, "Incorrect status code. Expected 302.");
        System.out.println("Status Code is 302.");
    }

    public void assertStatusCode400(int statusCode) {
        Assertions.assertEquals(SC_BAD_REQUEST, statusCode, "Incorrect status code. Expected 400.");
        System.out.println("Status Code is 400.");
    }

    public void assertStatusCode404(int statusCode) {
        Assertions.assertEquals(SC_NOT_FOUND, statusCode, "Incorrect status code. Expected 404.");
        System.out.println("Status Code is 404.");
    }

    public void assertStatusCode401(int statusCode) {
        Assertions.assertEquals(SC_UNAUTHORIZED, statusCode, "Incorrect status code. Expected 401.");
        System.out.println("Status Code is 401.");
    }

    public void assertListIsNotEmpty(List<Object> object) {
        Assertions.assertFalse(object.isEmpty());
        System.out.println("List is not empty.");
    }

    public void assertPostsArePublic(List<PublicPostsModel> posts) {
        for (var post : posts) {
            var publicField = post.mypublic;
            Assertions.assertTrue(publicField,
                    String.format("Post with ID %s is different than expected. " +
                            "Public type is %s.", post.postId, post.mypublic));
        }
        System.out.println("Posts are public.");
    }

    //-------------------------------//
    public void assertBadRequestError(Response response) {
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals("Bad Request", error, "Response does not have 'Bad Request' error.");
        System.out.println("Response has 'Bad Request' error.");
    }

    //--------------------------------//
    public void assertBadRequest(ErrorModel response) {
        String error = response.error;
        Assertions.assertEquals(BAD_REQUEST, error,
                String.format("Response does not have '%s' error. Error is %s", BAD_REQUEST, error));
        System.out.println("Response has 'Bad Request' error.");
    }

    public void assertNotFound(ErrorModel response) {
        String error = response.error;
        Assertions.assertEquals(NOT_FOUND, error,
                String.format("Response does not have '%s' error. Error is %s", NOT_FOUND, error));
        System.out.println("Response has 'Not Found' error.");
    }

    public void assertUnauthorizedError(ErrorModel response) {
        String error = response.error;
        Assertions.assertEquals(UNAUTHORIZED_ERROR, error,
                String.format("Response does not have '%s' error. Error is %s", UNAUTHORIZED_ERROR, error));
        System.out.println("Response has 'Unauthorized' error.");
    }

    public void assertBadRequestMessage(ErrorModel response) {
        String message = response.message;
        Assertions.assertTrue(message.contains(REGISTRATION_ERROR_MESSAGE),
                "Error message is different than expected.");
        System.out.println("Error message is correct.");
    }

    public void assertNotFoundMessage(ErrorModel response) {
        String message = response.message;
        Assertions.assertEquals(NOT_FOUND_ERROR_MESSAGE, message,
                "Error message is different than expected.");
        System.out.println("Error message is correct.");
    }

    public void assertUnauthorizedMessage(ErrorModel response) {
        String message = response.message;
        Assertions.assertEquals(UNAUTHORIZED_ERROR_MESSAGE, message,
                "Error message is different than expected.");
        System.out.println("Error message is correct.");
    }

    public void assertUserId(UserInformationModel user, int expectedId) {
        Assertions.assertEquals(user.id, expectedId, "Expected user ID is different than actual.");
        System.out.println("User ID is correct.");
    }

    public void assertUserId(EditProfileModel user, int expectedId) {
        Assertions.assertEquals(user.id, expectedId, "Expected user ID is different than actual.");
        System.out.println("User ID is correct.");
    }

    public void assertFirstName(EditProfileModel user, String expectedFirstName) {
        Assertions.assertEquals(user.firstName, expectedFirstName,
                "Expected first name is different than actual.");
        System.out.println("First name is correct.");
    }

    public void assertLastName(EditProfileModel user, String expectedLastName) {
        Assertions.assertEquals(user.lastName, expectedLastName,
                "Expected last name is different than actual.");
        System.out.println("Last name is correct.");
    }

    public void assertBirthDate(EditProfileModel user, String birthDate) {
        Assertions.assertEquals(user.birthYear, birthDate, "Expected birth date is different than actual.");
        System.out.println("Birth date is correct.");
    }

    public void assertUserUsername(UserInformationModel user, String expectedUsername) {
        Assertions.assertEquals(user.username, expectedUsername, "Expected username is different than actual.");
        System.out.println("Username is correct.");
    }

    public void assertNotEqual(String expect, String actual) {
        Assertions.assertNotEquals(expect, actual, "Expected name is the same than actual.");
        System.out.println("Name is different.");
    }

    public void assertInvalidPostContent(Response response) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains("Content size must be up to 1000 symbols"),
                "Invalid post content message not found in the response.");
        System.out.println("Content size must be up to 1000 symbols");
    }

    public void assertPostContent(String response) {
       // String responseBody = response.getBody().asString();
        Assertions.assertEquals(POST_DESCRIPTION_VALID, response,
                String.format("Expected content '%s' not found in the response.", response));
        System.out.println("New post has been created.");
    }

    public void assertPostIsPublic(Response response) {
        boolean isPublic = response.jsonPath().getBoolean("public");
        Assertions.assertTrue(isPublic, "Post is not public.");
        System.out.println("Post is public.");
    }

    public void assertPostIsNotPublic(Response response) {
        boolean isPublic = response.jsonPath().getBoolean("public");
        Assertions.assertFalse(isPublic, "Post should not be public.");
        System.out.println("Post is not public.");
    }

    public void assertPostIsLiked(Response response) {
        boolean liked = response.jsonPath().getBoolean("liked");
        Assertions.assertTrue(liked, "Post was not liked");
        System.out.println("Post was liked.");
    }

    public void assertPostIsDisliked(Response response) {
        boolean liked = response.jsonPath().getBoolean("liked");
        Assertions.assertFalse(liked, "Post was not disliked");
        System.out.println("Post was disliked.");
    }

    public void assertResponseBodyIsEmpty(Response response) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body is not empty.");
        System.out.println("Response body is empty.");
    }

    public void assertUsersContainSearchedName(List<SearchModel> users, String searchedUsername) {
        boolean found = false;
        for (var user : users) {
            if (user.username.equals(searchedUsername)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found,
                String.format("Searched username %s not found in the list.", searchedUsername));
        System.out.println("Searched username found.");
    }


    public void assertPostIdIsPositive(int postId) {
        Assertions.assertTrue(postId > 0, "Post ID is not a positive number.");
        System.out.println("Post ID is positive.");
    }

    public void assertContentIsExpected(String actualContent, String expectedContent) {
        Assertions.assertEquals(expectedContent, actualContent, "Content is not as expected.");
        System.out.println("Content is as expected.");
    }

    public void assertCategoryIdIsExpected(int categoryId, int expectedCategoryId) {
        Assertions.assertEquals(expectedCategoryId, categoryId, "Category ID is not as expected.");
        System.out.println("Category ID is as expected.");
    }

    public void assertCategoryNameIsExpected(String actualCategoryName, String expectedCategoryName) {
        Assertions.assertEquals(expectedCategoryName, actualCategoryName, "Category name is not as expected.");
        System.out.println("Category name is as expected.");
    }

    public void assertCommentIsLiked(Response response) {
        boolean liked = response.jsonPath().getBoolean("liked");
        Assertions.assertTrue(liked, "Comment was not liked");
        System.out.println("Comment was liked.");
    }

    public void assertCommentIsDisliked(Response response) {
        boolean liked = response.jsonPath().getBoolean("liked");
        Assertions.assertFalse(liked, "Comment was not disliked");
        System.out.println("Comment was disliked.");
    }

    public void assertRegistrationMessage(Response responseMessage) {
        var response = baseMethods.searchUsersByEmptyName();
        List<SearchModel> users = baseMethods.getListOfUsers(response);
        var lastUser = users.get(0);
        var lastUserId = lastUser.userId;
        var expectedMessage = String.format("User with name %s and id %s was created", RANDOM_USERNAME, lastUserId);
        var actualMessage = responseMessage.asString();
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected message is different than actual.");
        System.out.println("Registration message is correct!");
        System.out.printf("User with ID %s is registered.", lastUserId);
        LAST_REGISTERED_USER_ID = lastUserId;
    }

    public void assertSearchedFirstNameContainsInUserProfile(List<SearchModel> users, String firstName) {
        for (var user : users) {
            var username = user.username;
            var userId = user.userId;
            var userInformation = baseMethods.getUserInformation(username, userId);
            var userModel = userInformation.as(UserInformationModel.class);
            Assertions.assertEquals(userModel.firstName, firstName,
                    String.format("First name is different than expected: %s.", userModel.firstName));
        }
        System.out.println("First name is correct.");
    }

    public void assertSentFriendRequestMessage(Response friendRequestResponse, String sendRequestUsername, String recieveRequestUsername) {
        String expectedMessage = friendRequestResponse.asString();
        String actualMessage = String.format("%s send friend request to %s", sendRequestUsername, recieveRequestUsername);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected message is different than actual." +
                String.format("Actual message: %s", actualMessage) +
                String.format("Expected message: %s", expectedMessage));
        System.out.println("Request message is correct.");
    }

    public void assertDuplicatedRequestErrorMessage(Response friendRequestResponse, String sendRequestUsername, String recieveRequestUsername) {
        String expectedMessage = friendRequestResponse.asString();
        String actualMessage = String.format("%s can't connect to %s", sendRequestUsername, recieveRequestUsername);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected message is different than actual." +
                String.format("Actual message: %s", actualMessage) +
                String.format("Expected message: %s", expectedMessage));
        System.out.println("Request message is correct.");

    }

    public void assertSearchedLastNameContainsInUserProfile(List<SearchModel> users, String lastName) {
        for (var user : users) {
            var username = user.username;
            var userId = user.userId;
            var userInformation = baseMethods.getUserInformation(username, userId);
            var userModel = userInformation.as(UserInformationModel.class);
            Assertions.assertTrue(lastName.contains(userModel.lastNAme),
                    String.format("Last name is different than expected: %s.", userModel.lastNAme));
        }
        System.out.println("Last name is correct.");
    }

    public void assertEquals(UserInformationModel unchangedUser, UserInformationModel userProfile) {
        Assertions.assertEquals(userProfile.firstName, unchangedUser.firstName,
                "Expected first name is different than actual.");
        Assertions.assertEquals(userProfile.lastNAme, unchangedUser.lastNAme,
                "Expected last name is different than actual.");
        Assertions.assertEquals(userProfile.birthYear, unchangedUser.birthYear,
                "Expected birth date is different than actual.");
        Assertions.assertEquals(userProfile.email, unchangedUser.email,
                "Expected email is different than actual.");
        Assertions.assertEquals(userProfile.username, unchangedUser.username,
                "Expected username is different than actual.");
        System.out.println("There are no differences between user information.");
    }

    public void assertSuccessfulAcceptMessage(String responseMessage) {
        var expectedText = "approved request of";
        Assertions.assertTrue(responseMessage.contains(expectedText), "Actual message doesn't contain " +
                "expected text. " + String.format("Actual message: %s", responseMessage));
        System.out.println("Actual message contains expected text.");
    }

}

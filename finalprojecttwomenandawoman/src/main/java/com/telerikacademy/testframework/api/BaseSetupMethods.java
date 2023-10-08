package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.*;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class BaseSetupMethods {

    private RequestSpecification getRestAssured() {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(BASE_API_URL);
    }

    public Response browseAllPublicPosts() {
        return getRestAssured()
                .when()
                .get(LATEST_POSTS)
                .then()
                .extract()
                .response();
    }

    public List<PublicPostsModel> getListOfPosts(Response response) {
        return Arrays.asList(response.getBody().as(PublicPostsModel[].class));
    }

    public Response editUserProfile(String username, String password,
                                    String birthday, String firstName, int id, String lastName) {
        var body = String.format(EDIT_USER_PROFILE_BODY, birthday, firstName, id, lastName);

        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .body(body)
                .when()
                .post(String.format(EDIT_USER_PROFILE, id))
                .then()
                .extract()
                .response();
    }

    public Response registerUser(String jobTitle, String password, String email, String username) {
        var body = String.format(REGISTRATION_BODY, jobTitle, password, email, password, username);
        return getRestAssured()
                .body(body)
                .when()
                .post(REGISTER_USER)
                .then()
                .extract().response();
    }

    public Response searchUsersByName(String jobTitle, String name) {
        var body = String.format(SEARCH_BY_JOB_TITLE_AND_NAME_BODY, jobTitle, name);

        return getRestAssured()
                .body(body)
                .when()
                .post(USERS);
    }

    public Response searchUsersByEmptyName() {
        var body = String.format(SEARCH_BY_JOB_TITLE_AND_NAME_BODY, EMPTY_STRING, EMPTY_STRING);

        return getRestAssured()
                .body(body)
                .when()
                .post(USERS);
    }

    public List<SearchModel> getListOfUsers(Response response) {
        return Arrays.asList(response.getBody().as(SearchModel[].class));
    }

    public List<FriendsRequestModel> getListOfRequests(Response response) {
        return  Arrays.asList(response.getBody().as(FriendsRequestModel[].class));
    }

    public ErrorModel convertErrorBody(Response response) {
        return response.as(ErrorModel.class);
    }

    public Response createPublicPost(String username, String password, String description) {
        RestAssured.baseURI = BASE_API_URL;
        String body = String.format(CREATE_PUBLIC_POST_BODY, description);

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .body(body)
                .post(CREATE_POST);
    }

    public Response createPrivatePost(String username, String password, String description) {
        RestAssured.baseURI = BASE_API_URL;
        String body = String.format(CREATE_PRIVATE_POST_BODY, description);

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .body(body)
                .post(CREATE_POST);
    }

    public Response editPublicPost(String username, String password, String newDescription, int lastPostId) {
        RestAssured.baseURI = BASE_API_URL;
        String body = String.format(EDIT_PUBLIC_POST_BODY, newDescription);

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("postId", lastPostId)
                .body(body)
                .put(EDIT_POST);
    }

    public Response editPrivatePost(String username, String password, String newDescription, int lastPostId) {
        String body = String.format(EDIT_PRIVATE_POST_BODY, newDescription);

        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("postId", lastPostId)
                .body(body)
                .put(EDIT_POST);
    }

    public Response deletePost(String username, String password, int lastPostId) {
        RestAssured.baseURI = BASE_API_URL;

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("postId", lastPostId)
                .delete(DELETE_POST);
    }

    public Response getLastPublicPost(int lastPostId) {
        return getRestAssured()
                .when()
                .get(LATEST_POSTS + lastPostId)
                .then()
                .extract()
                .response();
    }

    public Response likePost(String username, String password, int lastPostId) {
        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("postId", lastPostId)
                .post(LIKE_POST)
                .then()
                .extract()
                .response();
    }

    public Response dislikePost(String username, String password, int lastPostId) {
        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("postId", lastPostId)
                .post(DISLIKE_POST)
                .then()
                .extract()
                .response();
    }

    public Response createComment(String username, String password, String description, int lastPostId) {
        //  RestAssured.baseURI = BASE_API_URL;
        String body = String.format(CREATE_COMMENT_BODY, description, lastPostId, TOM_CRUISE_ID);

        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .body(body)
                .post(CREATE_COMMENT)
                .then()
                .extract()
                .response();
    }

    public Response editComment(String username, String password, String newContent, int lastCommentId) {
        RestAssured.baseURI = BASE_API_URL;
        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("commentId", lastCommentId)
                .queryParam("content", newContent)
                .queryParam("name", username)
                .put(EDIT_COMMENT);
    }

    public Response deleteComment(String username, String password, int lastCommentId) {
        RestAssured.baseURI = BASE_API_URL;
        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("commentId", lastCommentId)
                .delete(DELETE_COMMENT);
    }

    public Response likeComment(String username, String password, int commentId) {
        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("commentId", commentId)
                .post(LIKE_COMMENT)
                .then()
                .extract()
                .response();
    }

    public Response dislikeComment(String username, String password, int commentId) {
        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .queryParam("commentId", commentId)
                .post(DISLIKE_COMMENT)
                .then()
                .extract()
                .response();
    }

    // https://stackoverflow.com/questions/40317130/apis-http-response-yields-the-entire-html-page-instead-of-the-responses-body

    public Response signInUser(String username, String password) {

        RestAssured.baseURI = BASE_URL;

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .when()
                .post(AUTHENTICATE);
    }

    public Response getUserInformation(String username, int userId) {
        return getRestAssured()
                .queryParam("principal", username)
                .when()
                .get(String.format(GET_USER_INFO, userId))
                .then()
                .extract()
                .response();
    }

    public Response sendFriendRequest(String username, String password, int id, String userName){
        var body = String.format(FRIENDS_REQUEST_BODY, id, userName);

        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .body(body)
                .when()
                .post(SEND_REQUEST)
                .then()
                .extract()
                .response();
    }

    public Response getUserRequest(String username, String password, int id) {
        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig
                        (AUTHENTICATE, "username", "password"))
                .when()
                .get(String.format(GET_USER_REQUESTS, id))
                .then()
                .extract()
                .response();
    }

    public String approveFriendRequest(String acceptingUsername, String acceptingPassword,
                                         int userAcceptingId, int userSendingId) {
        return getRestAssured()
                .auth()
                .form(acceptingUsername, acceptingPassword, new FormAuthConfig(AUTHENTICATE,
                        "username", "password"))
                .queryParam("requestId", userSendingId)
                .when()
                .post(String.format(APPROVE_REQUESTS, userAcceptingId))
                .then()
                .extract()
                .response()
                .asString();
    }

    //############# Asserts #############

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

    public void assertPostContent(Response response, String expectedContent) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains(expectedContent),
                String.format("Expected content '%s' not found in the response.", expectedContent));
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
        for (var user : users) {
            var username = user.username;
            Assertions.assertEquals(searchedUsername, username,
                    String.format("Username is different than expected: %s.", username));
        }
        System.out.println("Usernames are correct.");
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
        var response = searchUsersByEmptyName();
        List<SearchModel> users = getListOfUsers(response);
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
            var userInformation = getUserInformation(username, userId);
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
            var userInformation = getUserInformation(username, userId);
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
    public void generateRandomUsername() {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";
        int minLength = 2;
        int maxLength = 20;
        int length = generateRandomLength(maxLength, minLength);
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        RANDOM_USERNAME = sb.toString();
        RANDOM_EMAIL = sb.toString() + "user@abv.bg";
    }

    private int generateRandomLength(int maxLength, int minLength) {
        Random rand = new Random();
        return rand.nextInt(maxLength - minLength + 1) + minLength;
    }

    public String generateInvalidComment() {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz"
                + "0123456789 ";
        int length = 1001;
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}

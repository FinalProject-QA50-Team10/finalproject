package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.FriendsRequestModel;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;
import static io.restassured.RestAssured.given;

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

//    public String authenticateUser() {
//
//        String jsessionIdCookie = getRestAssured()
//                .formParam("username", getLatestRegisteredUsername(getAllUsers()))
//                .formParam("password", USER_PASSWORD)
//                .when()
//                .post("/authenticate")
//                .then().statusCode(302)
//                .extract().response().getCookie("JSESSIONID");
//        return jsessionIdCookie;
//    }

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
        return Arrays.asList(response.getBody().as(FriendsRequestModel[].class));
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
        String body = String.format(EDIT_PUBLIC_POST_BODY, newDescription);

        return getRestAssured()
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

    public Response sendFriendRequest(String username, String password, int id, String userName) {
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

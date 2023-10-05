package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.CommentModel;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import com.telerikacademy.testframework.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;
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

    public String registerUser() {
        return getRestAssured()
                .body(REGISTRATION_BODY)
                .when()
                .post(REGISTER_USER)
                .then()
                .extract()
                .asString();
    }

    public Response searchUsers(String name) {
        var body = String.format(SEARCH_BY_FIRST_AND_LAST_NAME_BODY, name);

        return getRestAssured()
                .body(body)
                .when()
                .post(USERS);
    }

    public List<SearchModel> getListOfUsers(Response response) {
        return Arrays.asList(response.getBody().as(SearchModel[].class));
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

    public Response editPublicPost(String username, String password, String newDescription, int lastPostId) {
        RestAssured.baseURI = BASE_API_URL;
        String body = String.format(EDIT_PUBLIC_POST_BODY, newDescription);

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .body(body)
                .put(CREATE_POST + lastPostId);
    }

    public Response createComment(String username, String password, String description, int lastPostId) {
        RestAssured.baseURI = BASE_API_URL;
        String body = String.format(CREATE_COMMENT_BODY, COMMENT_DESCRIPTION_VALID, lastPostId, USER_ID_FOR_TOM_CRUISE);

        return given()
                .auth()
                .form(username, password, new FormAuthConfig(AUTHENTICATE, "username", "password"))
                .contentType("application/json")
                .log().all()
                .body(body)
                .post(CREATE_COMMENT);
    }


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


    //############# Asserts #############

    public void assertStatusCodeIsOk(int statusCode) {
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

    public void assertListIsNotEmpty(List<Object> object) {
        Assertions.assertFalse(object.isEmpty());
        System.out.println("List is not empty.");
    }

    // da priniram v otg. koj e private
    public void assertPostsArePublic(List<PublicPostsModel> posts) {
        for (var post : posts) {
            var publicField = post.mypublic;
            Assertions.assertTrue(publicField,
                    String.format("Post with ID %s is different than expected. Public type is %s.", post.postId, post.mypublic));
        }
        System.out.println("Posts are public.");
    }

    public void assertBadRequestError(Response response) {
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals("Bad Request", error, "Response does not have 'Bad Request' error.");
        System.out.println("Response has 'Bad Request' error.");
    }

    public void assertPostContent(Response response, String expectedContent) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains(expectedContent),
                String.format("Expected content '%s' not found in the response.", expectedContent));
        System.out.println("New public post has been created.");
    }

    public void assertPostIsPublic(Response response) {
        boolean isPublic = response.jsonPath().getBoolean("public");
        Assertions.assertTrue(isPublic, "Post is not public.");
        System.out.println("Post is public.");
    }

    public void assertUsername(List<SearchModel> users, String name) {
        for (var user : users) {
            var username = user.username;
            Assertions.assertEquals(name, username,
                    String.format("Username is different than expected: %s.", username));
        }
        System.out.println("Usernames are correct than expected.");
    }
}

package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.PublicPostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class BaseSetupMethods {

    private RequestSpecification getRestAssured()
    {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(BASE_API_URL);
    }

    public Response browseAllPublicPosts(){
        return getRestAssured()
                .when()
                .get(LATEST_POSTS)
                .then()
                .extract()
                .response();
    }

    public List<PublicPostsModel> getListOfPosts(Response response)
    {
       return Arrays.asList(response.getBody().as(PublicPostsModel[].class));
    }

    public String registerUser(){
        return getRestAssured()
                .body(REGISTRATION_BODY)
                .when()
                .post(REGISTER_USER)
                .then()
                .extract()
                .asString();
    }

    public Response searchUsers(){
        var body = String.format(SEARCH_BY_FIRST_AND_LAST_NAME_BODY, GEORGE_BUSH_NAME);

        return getRestAssured()
                .body(body)
                .when()
                .post(USERS);
    }

    public List<SearchModel> getListOfUsers(Response response)
    {
        return Arrays.asList(response.getBody().as(SearchModel[].class));
    }

    public Response signInWithUserMrBeast() {
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .auth()
                .form(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, new FormAuthConfig(AUTHENTICATE, MR_BEAST_USERNAME, MR_BEAST_PASSWORD))
                .post(AUTHENTICATE);

        return response;
    }

          //############# Asserts #############

    public void assertStatusCodeIsOk(int statusCode)
    {
        Assertions.assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        System.out.println("Status Code is 200.");
    }

    public void assertStatusCode302(int statusCode)
    {
        Assertions.assertEquals(SC_MOVED_TEMPORARILY, statusCode, "Incorrect status code. Expected 302.");
        System.out.println("Status Code is 302.");
    }

    public void assertListIsNotEmpty(List<Object> object) {
        Assertions.assertFalse(object.isEmpty());
        System.out.println("List is not empty.");
    }

    // da priniram v otg. koj e private
    public void assertPostsArePublic(List<PublicPostsModel> posts)
    {
        for (var post : posts) {
            var publicField = post.mypublic;
            Assertions.assertTrue(publicField,
                    String.format("Post is different than expected."));
        }
        System.out.println("Posts are public.");
    }

    public void assertUsername(List<SearchModel> users)
    {
        for (var user : users) {
            var username = user.username;
            Assertions.assertEquals(GEORGE_BUSH_USERNAME, username,
                    String.format("Username is different than expected: %s.", username));
        }
        System.out.println("Usernames are correct than expected.");
    }
}

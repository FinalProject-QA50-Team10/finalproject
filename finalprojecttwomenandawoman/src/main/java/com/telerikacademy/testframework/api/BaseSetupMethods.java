package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.api.models.PublicPostsModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class BaseSetupMethods {

    private RequestSpecification getRestAssured()
    {
        return RestAssured
                .given()
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


          //############# Asserts #############

    public void assertStatusCodeIsOk(int statusCode)
    {
        Assertions.assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        System.out.println("Status Code is 200.");
    }

    public void assertListIsNotEmpty(List<PublicPostsModel> posts) {
        Assertions.assertFalse(posts.isEmpty());
        System.out.println("Post list is not empty.");
    }

    public void assertPostsArePublic(List<PublicPostsModel> posts)
    {
        for (var post : posts) {
            var publicField = post.mypublic;
            Assertions.assertTrue(publicField,
                    String.format("Post is different than expected: %s.", post.toString()));
        }
        System.out.println("Posts are public.");
    }
}

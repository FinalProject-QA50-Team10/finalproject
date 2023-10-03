package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.PropertiesManager;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.*;

public class BaseSetupMethods {

    private RequestSpecification getRestAssured()
    {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("WEare.api.baseUrl"));
    }

    public Response browseAllPublicPosts(){
        return getRestAssured()
                .when()
                .get(LATEST_POSTS)
                .then()
                .extract()
                .response();
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

}

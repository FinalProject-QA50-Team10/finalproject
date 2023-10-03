package com.telerikacademy.testframework.api;

import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class BaseSetupMethods {

    private RequestSpecification getRestAssured()
    {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("WEare.api.baseUrl"));
    }

    public List<Response> browsePublicPosts(){
        return getRestAssured()
                .when()
                .get("/post/")
                .thenReturn()
                .jsonPath()
                .get("postId");
    }

}

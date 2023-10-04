package com.telerikacademy.testframework.api.utils;

import static java.lang.String.format;

public class Endpoints {

    //base urls
    public static final String BASE_URL = "https://fast-plains-73661-971f43405213.herokuapp.com";
    public static final String BASE_API_URL = BASE_URL + "/api";

    //authentication
    public static final String AUTHENTICATE = "/authenticate";

    //users
    public static final String USERS = "/users";
    public static final String GET_USERS = "/search";
    public static final String REGISTER_USER = "/users/";
    public static final String EDIT_USER_PROFILE = format("%s%s", BASE_API_URL, "/users/auth/%s/personal/");

    //requests
    public static final String SEND_REQUEST = BASE_API_URL + "/auth/request";
    public static final String GET_USER_REQUESTS = format("%s%s", BASE_API_URL, "/auth/users/%s/request/");
    public static final String APPROVE_REQUESTS = format("%s%s", BASE_API_URL, "/auth/users/%s/request/approve");

    //posts
    public static final String CREATE_POST = "/post/auth/creator";
    public static final String EDIT_POST = BASE_API_URL + "/post/auth/editor";
    public static final String DELETE_POST = BASE_API_URL + "/post/auth/manager";
    public static final String LATEST_POSTS = "/post/";
    public static final String LIKE_POST = BASE_API_URL + "/post/auth/likesUp";

    //comments
    public static final String CREATE_COMMENT = BASE_API_URL + "/comment/auth/creator";
    public static final String EDIT_COMMENT = BASE_API_URL + "/comment/auth/editor";
    public static final String DELETE_COMMENT = BASE_API_URL + "/comment/auth/manager";
    public static final String LIKE_COMMENT = BASE_API_URL + "/comment/auth/likesUp";

}

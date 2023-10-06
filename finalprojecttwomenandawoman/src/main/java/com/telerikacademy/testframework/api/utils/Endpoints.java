package com.telerikacademy.testframework.api.utils;

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
    public static final String EDIT_USER_PROFILE = "/users/auth/%s/personal";
    public static final String GET_USER_INFO = "/users/auth/%d";

    //requests
    public static final String SEND_REQUEST = "/auth/request";
    public static final String GET_USER_REQUESTS = "/auth/users/%s/request/";
    public static final String APPROVE_REQUESTS = "/auth/users/%s/request/approve";

    //posts
    public static final String CREATE_POST = "/post/auth/creator";
    public static final String EDIT_POST = "/post/auth/editor";
    public static final String DELETE_POST = "/post/auth/manager";
    public static final String LATEST_POSTS = "/post/";
    public static final String LIKE_POST = "/post/auth/likesUp";

    //comments
    public static final String CREATE_COMMENT = "/comment/auth/creator";
    public static final String EDIT_COMMENT = "/comment/auth/editor";
    public static final String DELETE_COMMENT = "/comment/auth/manager";
    public static final String LIKE_COMMENT = "/comment/auth/likesUp";
    public static final String DISLIKE_COMMENT = "/comment/auth/likesUp";

}

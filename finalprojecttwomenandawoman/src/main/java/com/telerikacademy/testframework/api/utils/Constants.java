package com.telerikacademy.testframework.api.utils;

public class Constants {

    public static final String GEORGE_BUSH_NAME ="George Bush";
    public static final String GEORGE_BUSH_USERNAME ="GeorgeBush";
    public static final String MR_BEAST_USERNAME = "MrBeast";
    public static final String MR_BEAST_PASSWORD = "Abv123!";
    public static final String JACK_NICHOLSON_USERNAME = "JackNicholson";
    public static final String JACK_NICHOLSON_PASSWORD = "TheJack123";
    public static final String POST_DESCRIPTION_INVALID = "";
    public static final String POST_DESCRIPTION_VALID = "Initial Post by MrBeast using Postman";

    public static  final  String REGISTRATION_BODY = "{\"authorities\": [\"\"], " +
            "\"category\": " +
            "{\"id\": 150," +
            "\"name\": \"{{validJobTitle}}\"}," +
            "\"confirmPassword\": \"{{MrBeastPassword}}\"," +
            "\"email\": \"{{randomEmail}}\"," +
            "\"password\": \"{{MrBeastPassword}}\"," +
            "\"username\": \"{{randomUsername}}\"}";

    public static final String SEARCH_BY_FIRST_AND_LAST_NAME_BODY = "{\"index\": 0, " +
            "\"next\": true," +
            "\"searchParam1\": \"\"," +
            "\"searchParam2\": \"%s\"," +
            "\"size\": 20" +
            "}";

    public static final String CREATE_PUBLIC_POST_BODY = "{\"content\": \"%s\"," +
            "\"picture\": \"\"," +
            "\"public\": true" +
            "}";
}




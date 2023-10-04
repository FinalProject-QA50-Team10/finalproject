package com.telerikacademy.testframework.api.utils;

public class Constants {

    public static final String GEORGE_BUSH_NAME ="George Bush";
    public static final String GEORGE_BUSH_USERNAME ="GeorgeBush";
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
}

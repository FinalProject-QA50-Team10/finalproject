package com.telerikacademy.testframework.api.utils;

public class Constants {
    public static final String ADMIN_NAME = "adminYordan";
    public static final String ADMIN_PASSWORD = "Abv123!";
    public static final String GEORGE_BUSH_NAME = "George Bush";
    public static final String GEORGE_BUSH_USERNAME = "GeorgeBush";
    public static final String GEORGE_BUSH_FIRST_NAME = "George";
    public static final String GEORGE_BUSH_LAST_NAME = " Bush";
    public static final String GEORGE_BUSH_PASSWORD = "Abv123*";
    public static final String MR_BEAST_USERNAME = "MrBeast";
    public static final String MR_BEAST_PASSWORD = "Abv123!";
    public static final String JACK_NICHOLSON_USERNAME = "JackNicholson";
    public static final String JACK_NICHOLSON_PASSWORD = "TheJack123";
    public static final String TOM_CRUISE_USERNAME = "TomCruise";
    public static final String TOM_CRUISE_PASSWORD = "HaHaHa123!";
    public static final int TOM_CRUISE_ID = 188;
    public static final String FOR_EDIT_USERNAME = "telerikdani";
    public static final String FOR_EDIT_PASSWORD = "123456";
    public static final int FOR_EDIT_ID = 39;
    public static final String FOR_EDIT_BIRTHDAY = "1955-07-17";
    public static final String FOR_EDIT_FIRST_NAME = "Ryan";
    public static final String FOR_EDIT_LAST_NAME = "Gosling";
    public static final int INVALID_ID = 800;
    public static String RANDOM_USERNAME;
    public static String  RANDOM_EMAIL;
    public static int LAST_REGISTERED_USER_ID;
    public static String EMPTY_STRING = "";
    public static int EMPTY_INTEGER;
    public static String INVALID_EMAIL = "invalid email@weare.com";
    public static String  VALID_JOB_TITLE = "Author";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not Found";
    public static final String UNAUTHORIZED_ERROR = "Unauthorized";
    public static final String INVALID_NAME = "Джордж Буш";
    public static final String REGISTRATION_ERROR_MESSAGE = "Validation failed for object";
    public static final String NOT_FOUND_ERROR_MESSAGE = "USER not found";
    public static final String UNAUTHORIZED_ERROR_MESSAGE = "User is not authorised";

    public static final String POST_DESCRIPTION_VALID = "Valid Post";
    public static final String EDIT_POST_DESCRIPTION_VALID = "Edit Valid Post";
    public static final String EDIT_POST_AS_ADMIN = "Edit Post as Admin";
    public static final String COMMENT_DESCRIPTION_VALID = "Valid Comment";
    public static final String EDIT_COMMENT_CONTENT = "Edit comment";
    public static final String POST_DESCRIPTION_INVALID = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
            " Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient" +
            " montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
            "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. " +
            "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
            "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus." +
            " Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
            "Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus " +
            "viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. " +
            "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. " +
            "Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, " +
            "sit amet adipiscing sem neque sed ipsum. Na";
    public static String REGISTRATION_BODY = "{\"authorities\": [\"\"], " +
            "\"category\": " +
            "{\"id\": 150," +
            "\"name\": \"%s\"}," +
            "\"confirmPassword\": \"%s\"," +
            "\"email\": \"%s\"," +
            "\"password\": \"%s\"," +
            "\"username\": \"%s\"" +
            "}";

    public static final String SEARCH_BY_JOB_TITLE_AND_NAME_BODY = "{\"index\": 0, " +
            "\"next\": true," +
            "\"searchParam1\": \"%s\"," +
            "\"searchParam2\": \"%s\"," +
            "\"size\": 20" +
            "}";

    public static final String CREATE_PUBLIC_POST_BODY = "{\"content\": \"%s\"," +
            "\"picture\": \"\"," +
            "\"public\": true" +
            "}";

    public static final String CREATE_PRIVATE_POST_BODY = "{\"content\": \"%s\"," +
            "\"picture\": \"\"," +
            "\"public\": false" +
            "}";

    public static final String EDIT_PUBLIC_POST_BODY = "{\"content\": \"%s\"," +
            "\"picture\": \"\"," +
            "\"public\": true" +
            "}";

    public static final String EDIT_PRIVATE_POST_BODY = "{\"content\": \"%s\"," +
            "\"picture\": \"\"," +
            "\"public\": false" +
            "}";

    public static final String CREATE_COMMENT_BODY = "{\"commentId\": 0," +
            "\"content\": \"%s\"," +
            "\"deletedConfirmed\": true, " +
            "\"postId\": %s, " +
            "\"userId\": %s" +
            "}";

    public static final String EDIT_USER_PROFILE_BODY = "{\"birthYear\": \"%s\"," +
            "\"firstName\": \"%s\"," +
            "\"id\": %s," +
            "\"lastName\": \"%s\"," +
            "\"location\": {" +
            "\"city\": {" +
            "\"city\": \"Varna\"," +
            "\"country\": {}," +
            "\"id\": 3" +
            "}," +
            "\"id\": 0" +
            "}," +
            "\"memberSince\": \"\"," +
            "\"personalReview\": \"\"," +
            "\"picture\": \"\"," +
            "\"picturePrivacy\": true," +
            "\"sex\": \"FEMALE\"" +
            "}";

    public static final String FRIENDS_REQUEST_BODY = "{\"id\": %s," +
            "\"username\": \"%s\"" +
            "}";
}




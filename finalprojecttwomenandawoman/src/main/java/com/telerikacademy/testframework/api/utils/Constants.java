package com.telerikacademy.testframework.api.utils;

public class Constants {

    public static final String GEORGE_BUSH_NAME ="George Bush";
    public static final String GEORGE_BUSH_USERNAME ="GeorgeBush";
    public static final String MR_BEAST_USERNAME = "MrBeast";
    public static final String MR_BEAST_PASSWORD = "Abv123!";
    public static final String POST_DESCRIPTION_INVALID = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis " +
            "parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, " +
            "pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, " +
            "aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. " +
            "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum " +
            "semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, " +
            "eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. " +
            "Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. " +
            "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. " +
            "Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, " +
            "sit amet adipiscing sem neque sed ipsum. Na";
    public static final String POST_DESCRIPTION_VALID = "Valid Post";

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




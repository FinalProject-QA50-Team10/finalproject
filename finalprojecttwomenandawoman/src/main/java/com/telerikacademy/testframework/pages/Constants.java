package com.telerikacademy.testframework.pages;

public class Constants {

    //########### URL ################
    public static final String HOME_PAGE = "WEare.homePage";
    public static final String LOGIN_PAGE = "WEare.loginPage";
    public static final String REGISTER_PAGE = "WEare.registerPage";
    public static final String SEARCHED_USERS_PAGE = "WEare.searchedUsersPage";

    //############### USER DATA ###############
    public static final String ADMIN_NAME = "WEare.admin.username";
    public static final String ADMIN_PASSWORD = "WEare.admin.password";
    public static final String GEORGE_BUSH_NAME = "George Bush";
    public static final String GEORGE_BUSH_USERNAME = "WEare.GeorgeBush.username";
    public static final String GEORGE_BUSH_FIRST_NAME = "George";
    public static final String GEORGE_BUSH_LAST_NAME = " Bush";
    public static final String GEORGE_BUSH_PASSWORD = "WEare.GeorgeBush.password";
    public static final String MR_BEAST_USERNAME = "WEare.MrBeast.username";
    public static final String MR_BEAST_PASSWORD = "WEare.MrBeast.password";
    public static final String JACK_NICHOLSON_USERNAME = "WEare.JackNicholson.username";
    public static final String JACK_NICHOLSON_PASSWORD = "WEare.JackNicholson.password";


//    public static final String TOM_CRUISE_USERNAME = "TomCruise";
//    public static final String TOM_CRUISE_PASSWORD = "HaHaHa123!";
//    public static final int TOM_CRUISE_ID = 188;
//    public static final String FOR_EDIT_USERNAME = "telerikdani";
//    public static final String FOR_EDIT_PASSWORD = "123456";
//    public static final int FOR_EDIT_ID = 39;
//    public static final String FOR_EDIT_BIRTHDAY = "1955-07-17";
//    public static final String FOR_EDIT_FIRST_NAME = "Ryan";
//    public static final String FOR_EDIT_LAST_NAME = "Gosling";
//    public static final int INVALID_ID = 800;

    public static final String INVALID_USERNAME = "Barbie";
    public static final String CYRILLIC_USERNAME = "Барби";
    public static final String VALID_JOB_TITLE = "Actor";
    public static final String INVALID_PASSWORD = "12";
    public static String INVALID_EMAIL = "invalid email@weare.com";
    public static String RANDOM_USERNAME;
    public static String RANDOM_EMAIL;

    //################ X-PATHS #################
    public static final String LOGIN_USERNAME_FIELD_PATH = "loginPage.usernameField";
    public static final String LOGIN_PASSWORD_FIELD_PATH = "loginPage.passwordField";
    public static final String LOGIN_BUTTON_PATH = "loginPage.loginButton";
    public static final String SIGN_IN_BUTTON_PATH = "homePage.signInButton";
    public static final String REGISTER_BUTTON_PATH = "homePage.registerButton";
    public static final String LOGOUT_BUTTON_PATH = "homePage.logOutButton";
    public static final String PERSONAL_PROFILE_BUTTON_PATH = "homePage.personalProfileButton";
    public static final String ADD_NEW_POST_BUTTON_PATH = "homePage.addNewPostButton";
    public static final String SEARCH_FORM_PATH = "homePage.searchForm";
    public static final String HOME_BUTTON_PATH = "loginPage.homeButton";
    public static final String LOG_OUT_BUTTON_PATH = "homePage.logOutButton";
    public static final String LOGIN_ERROR_MESSAGE_PATH = "loginPage.errorMessage";
    public static final String LOGOUT_ERROR_MESSAGE_PATH = "logoutPage.errorMessage";
    public static final String LOGIN_PAGE_TITLE_PATH = "loginPage.title";
    public static final String SEARCH_JOB_FIELD_PATH = "searchForm.jobTitleField";
    public static final String SEARCH_NAME_FIELD_PATH = "searchForm.nameField";
    public static final String SEARCH_BUTTON_PATH = "searchForm.searchButton";
    public static final String SEARCH_RESULTS_PATH = "searchForm.searchResults";
    public static final String SEARCH_RESULTS_JOB_TITLE_PATH = "searchForm.searchResults.jobTitle";
    public static final String SEARCH_RESULTS_NAME_PATH = "searchForm.searchResults.name";
    public static final String USERNAME_FIELD_PATH = "registrationPage.username";
    public static final String EMAIL_FIELD_PATH = "registrationPage.email";
    public static final String PASSWORD_FIELD_PATH = "registrationPage.password";
    public static final String CONFIRM_PASSWORD_FIELD_PATH = "registrationPage.confirmPassword";
    public static final String REGISTRATION_BUTTON_PATH = "registrationPage.registerButton";
    public static final String WELCOME_MESSAGE_PATH = "registeredUserPage.welcomeMessage";
    public static final String UPDATE_PROFILE_BUTTON_PATH = "registeredUserPage.updateProfileButton";
    public static final String REGISTRATION_ERROR_MESSAGE_PATH = "registrationPage.errorMessage";


    //##############  Admin user part #################
    public static final String ADMIN_ZONE_BUTTON = "admin.adminZoneButton";
    public static final String VIEW_USERS_BUTTON = "admin.viewUsersButton";
    public static final String SEE_USER_PROFILE_BUTTON = "admin.seeUserProfileButton";
    public static final String ENABLE_USER_BUTTON = "admin.enableUserButton";
    public static final String DISABLE_USER_BUTTON = "admin.disableUserButton";


    //##############  POSTS #################
    public static final String POSTS_URL = "WEare.latestPostPage";
    public static final String POSTS_ADD_NEW_POST = "posts.addNewPost";
    public static final String LATEST_POST_BUTTON = "posts.latestPost";
    public static final String EXPLORE_POST_BUTTON = "posts.explorePost";
    public static final String EDIT_POST_BUTTON = "posts.editPostButton";
    public static final String EDIT_POST_TEXT = "posts.editPostText";
    public static final String DELETE_POST_BUTTON = "posts.deletePostButton";
    public static final String DELETE_DROP_DOWN_MENU = "posts.deleteDropDownMenu";
    public static final String DELETE_BUTTON_SELECT = "posts.selectDeleteDropDown";
    public static final String DELETE_BUTTON_SUBMIT = "posts.submitButton";
    public static final String DELETE_POST_MESSAGE = "post.deleteMessage";
    public static final String POST_LIKE_BUTTON = "posts.likePost";
    public static final String POST_DISLIKE_BUTTON = "posts.dislikePostButton";
    public static final String POSTS_VISIBILITY = "posts.visibility";
    public static final String POSTS_PUBLIC_VISIBILITY = "posts.publicVisibility";
    public static final String POSTS_PRIVATE_VISIBILITY = "posts.privateVisibility";
    public static final String POST_MESSAGE_FIELD = "posts.messageField";
    public static final String POST_MESSAGE = "This is my first post with Selenium WebDriver";
    public static final String POST_MESSAGE_PRIVATE = "This is my first private post with Selenium WebDriver";
    public static final String POST_EDIT_MESSAGE = "Edit Public Post";
    public static final String POST_INVALID_TEXT_MESSAGE = "posts.postInvalidTextMessage";
    public static final String POST_SAVE_BUTTON = "posts.savePostButton";
    public static final String POST_TEXT = "posts.postText";
    public static final String POST_PRIVATE_TEXT = "posts.postTextPrivate";
    public static final String POST_IS_PUBLIC = "posts.postIsPublic";
    public static final String POST_IS_PRIVATE = "posts.postIsPrivate";
    public static final String BROWSE_POST_SELECTION_BUTTON = "posts.browsePublicPostsButton";


    //############## COMMENTS #################
    public static final String COMMENT_TEXT_FIELD = "comments.commentTextField";
    public static final String COMMENT_SUBMIT_BUTTON = "comments.submitCommentButton";
    public static final String COMMENT_SHOW_COMMENTS_BUTTON = "comments.showCommentsButton";
    public static final String EDIT_COMMENT_BUTTON = "comments.editCommentButton";
    public static final int INVALID_COMMENT_LENGTH = 1001;
    public static final String EDIT_COMMENT_SUBMIT_BUTTON = "comments.submitEditedCommentButton";
    public static final String DELETE_COMMENT_BUTTON = "comments.deleteCommentButton";
    public static final String DELETE_DROPDOWN_MENU = "comments.deleteCommentDropDownMenu";
    public static final String DELETE_DROPDOWN_SELECT = "comments.selectDeleteDropDown";
    public static final String DELETE_COMMENT_MESSAGE = "comments.deleteMessage";
    public static final String COMMENT_LIKE_BUTTON = "comments.likeComment";
    public static final String COMMENT_DISLIKE_BUTTON = "comments.dislikeComment";

    public static final String COMMENT_TEXT_MESSAGE = "This is a comment.";
    public static final String EDITED_COMMENT_TEXT_MESSAGE = "This is an edited comment.";
    public static final String COMMENT_TEXT = "comments.commentText";
    public static final String COMMENT_EDITED_TEXT = "comments.commentEditedText";
    public static final String COMMENT_INVALID_TEXT_MESSAGE = "comments.commentsInvalidTextMessage";


    //############## DESCRIPTIONS #################
    public static final String LOGIN_ERROR_MESSAGE = "Wrong username or password.";
    public static final String EMPTY_STRING = "";
    public static final int MIN_LENGTH_USERNAME = 2;
    public static final int MAX_LENGTH_USERNAME = 20;
    public static final String EMAIL_END = "user@abv.com";
    public static final String PASSWORD_ERROR_MESSAGE = "password must be minimum 6 characters";
    public static final String EMAIL_ERROR_MESSAGE = "this doesn't look like valid email";
    public static final String EXISTING_USERNAME_ERROR_MESSAGE = "User with this username already exist";
}

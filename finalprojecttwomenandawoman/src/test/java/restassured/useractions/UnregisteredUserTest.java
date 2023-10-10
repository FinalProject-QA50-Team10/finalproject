package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.PostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UnregisteredUserTest {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response unregisteredUser;

    @Test
    //FPT1-189 [Feed] Verify Unauthenticated Users Can Access Public Feed
    public void when_guestUserBrowsePublicPosts_expected_allPostsArePublic() {
        unregisteredUser = userActionsAPI.browseAllPublicPosts();
        List<PostsModel> posts = userActionsAPI.getListOfPosts(unregisteredUser);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(unregisteredUser.statusCode());
        // Assert that the list of posts is not empty
        assertions.assertListIsNotEmpty(Collections.singletonList(posts));
        // Assert that all posts are public
        assertions.assertPostsArePublic(posts);
    }

    @Test
    //FPT1-103 [Search Form] Search users with a valid first and valid second name as a guest
    public void when_guestUserSearchForUserWithValidName_expected_success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_NAME);
        List<SearchModel> users = assertions.getListOfUsers(unregisteredUser);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(unregisteredUser.statusCode());
        // Assert that the list of users is not empty
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        // Assert that the searched name appears in the user list
        assertions.assertUsersContainSearchedName(users, GEORGE_BUSH_USERNAME);
    }

    @Test
    //FPT1-101 [Search Form] Search users with empty name and empty job title as a guest
    public void when_guestUserSearchForUserWithEmptyNameAndEmptyJobTitle_expected_success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, EMPTY_STRING);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(unregisteredUser.statusCode());
        // Assert that the list of users is not empty
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
    }

    @Test
    //FPT1-104 [Search Form] Search for users with valid first name as a guest
    public void when_guestUserSearchForUserWithValidFirstName_expected_success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_FIRST_NAME);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(unregisteredUser.statusCode());
        // Assert that the list of users is not empty
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        // Assert that the first name appears in the user profiles
        assertions.assertSearchedFirstNameContainsInUserProfile(users, GEORGE_BUSH_FIRST_NAME);
    }

    @Test
    //FPT1-105 [Search Form] Search users with valid last name as a guest
    public void when_guestUserSearchForUserWithValidLastName_expected_success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_LAST_NAME);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        // Assert that the HTTP status code is 200 (OK)
        assertions.assertStatusCode200(unregisteredUser.statusCode());
        // Assert that the list of users is not empty
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        // Assert that the last name appears in the user profiles
        assertions.assertSearchedLastNameContainsInUserProfile(users, GEORGE_BUSH_LAST_NAME);
    }

    @Test
    //FPT1-226 [Search Form] Search users by invalid first and last name as a guest
    public void when_guestUserSearchForUserWithInvalidName_expected_notFound() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, INVALID_NAME);
        var errorModel = unregisteredUser.as(ErrorModel.class);

        // Assert that the HTTP status code is 404 (Not Found)
        assertions.assertStatusCode404(unregisteredUser.statusCode());
        // Assert that the error model indicates "Not Found"
        assertions.assertNotFound(errorModel);
        // Assert that the error message indicates "Not Found"
        assertions.assertNotFoundMessage(errorModel);
    }
}

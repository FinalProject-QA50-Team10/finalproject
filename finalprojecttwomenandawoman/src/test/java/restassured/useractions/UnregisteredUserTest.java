package restassured.useractions;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.ErrorModel;
import com.telerikacademy.testframework.api.models.PostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UnregisteredUserTest {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private Response unregisteredUser;

    @Test
    @Description("FPT1-421 [Public Feed] Verify Unauthenticated Users Can Access Public Feed")
    public void when_GuestUserBrowsesPublicPosts_expect_AllPostsArePublic() {
        unregisteredUser = userActionsAPI.browseAllPublicPosts();
        List<PostsModel> posts = userActionsAPI.getListOfPosts(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(posts));
        assertions.assertPostsArePublic(posts);
    }

    @Test
    @Description("FPT1-422 [Search Form] Search users with a valid first and valid second name as a guest")
    public void when_GuestUserSearchesForUserWithValidName_expect_Success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_NAME);
        List<SearchModel> users = assertions.getListOfUsers(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        assertions.assertUsersContainSearchedName(users, GEORGE_BUSH_USERNAME);
    }

    @Test
    @Description("FPT1-496 [Search Form] Search users with empty name and empty job title as a guest")
    public void when_GuestUserSearchesForUserWithEmptyNameAndEmptyJobTitle_expect_SuccessfulRegisteredUsersAreVisible() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, EMPTY_STRING);
        List<SearchModel> users = assertions.getListOfUsers(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
    }

    @Test
    @Description("FPT1-496 [Search Form] Search users with empty name and empty job title as a guest")
    public void when_GuestUserSearchForUserWithEmptyNameAndEmptyJobTitle_expected_Success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, EMPTY_STRING);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
    }

    @Test
    @Description("FPT1-493 [Search Form] Search users with valid first name as a guest")
    public void when_GuestUserSearchesForUserWithValidFirstName_expect_Success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_FIRST_NAME);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        assertions.assertSearchedFirstNameContainsInUserProfile(users, GEORGE_BUSH_FIRST_NAME);
    }

    @Test
    @Description("FPT1-494 [Search Form] Search users with valid last name as a guest")
    public void when_GuestUserSearchesForUserWithValidLastName_expect_Success() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, GEORGE_BUSH_LAST_NAME);
        List<SearchModel> users = userActionsAPI.getListOfUsers(unregisteredUser);

        assertions.assertStatusCode200(unregisteredUser.statusCode());
        assertions.assertListIsNotEmpty(Collections.singletonList(users));
        assertions.assertSearchedLastNameContainsInUserProfile(users, GEORGE_BUSH_LAST_NAME);
    }

    @Test
    @Description("FPT1-495 [Search Form] Search users by invalid first and last name as a guest")
    public void when_GuestUserSearchesForUserWithInvalidName_expect_NotFound() {
        unregisteredUser = userActionsAPI.searchUsersByName(EMPTY_STRING, INVALID_NAME);
        var errorModel = unregisteredUser.as(ErrorModel.class);

        assertions.assertStatusCode404(unregisteredUser.statusCode());
        assertions.assertNotFound(errorModel);
        assertions.assertNotFoundMessage(errorModel);
    }
}

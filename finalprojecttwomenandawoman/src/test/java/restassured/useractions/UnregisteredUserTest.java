package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import com.telerikacademy.testframework.api.models.SearchModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class UnregisteredUserTest {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();

    private Response browsePublicPostsResponse;
    private Response searchAsGuest;

    @Test
    public void when_guestUserBrowsePublicPosts_expected_allPostsArePublic()
    {
        browsePublicPostsResponse = userActionsAPI.browseAllPublicPosts();
        List<PublicPostsModel> posts = userActionsAPI.getListOfPosts(browsePublicPostsResponse);

        userActionsAPI.assertStatusCodeIsOk(browsePublicPostsResponse.statusCode());
        userActionsAPI.assertListIsNotEmpty(Collections.singletonList(posts));
        userActionsAPI.assertPostsArePublic(posts);
    }

    @Test
    public void when_guestUserSearchForUserWithValidName_expected_success()
    {
        searchAsGuest = userActionsAPI.searchUsersByName(GEORGE_BUSH_NAME);
        List<SearchModel> users = userActionsAPI.getListOfUsers(searchAsGuest);

        userActionsAPI.assertStatusCodeIsOk(searchAsGuest.statusCode());
        userActionsAPI.assertListIsNotEmpty(Collections.singletonList(users));
        userActionsAPI.assertUsername(users, GEORGE_BUSH_USERNAME);
    }

//    @Test
//    public void when_guestUserSearchForUserWithValidFirstName_expected_success()
//    {
//        searchAsGuest = userActionsAPI.searchUsers(GEORGE_BUSH_FIRST_NAME);
//        List<SearchModel> users = userActionsAPI.getListOfUsers(searchAsGuest);
//
//        userActionsAPI.assertStatusCodeIsOk(searchAsGuest.statusCode());
//        userActionsAPI.assertListIsNotEmpty(Collections.singletonList(users));
//        userActionsAPI.assertUsername(users, GEORGE_BUSH_USERNAME);
//    }
}

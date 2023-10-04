package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import java.util.List;

public class UnregisteredUserTest {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();

    private Response browsePublicPostsResponse;

    @Test
    public void when_guestUser_expected_browsePublicPosts()
    {
        browsePublicPostsResponse = userActionsAPI.browseAllPublicPosts();
        List<PublicPostsModel> posts = userActionsAPI.getListOfPosts(browsePublicPostsResponse);

        userActionsAPI.assertStatusCodeIsOk(browsePublicPostsResponse.statusCode());
        userActionsAPI.assertListIsNotEmpty(posts);
        userActionsAPI.assertPostsArePublic(posts);
    }


}

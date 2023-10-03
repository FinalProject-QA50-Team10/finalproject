package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import java.util.Arrays;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

public class UserActionsTest extends BaseTestSetupBeforeAfter {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();

    private Response browsePublicPostsResponse;

    @Test
    public void browseAllPublicPosts()
    {
        browsePublicPostsResponse = userActionsAPI.browseAllPublicPosts();
        List<PublicPostsModel> posts = Arrays.asList(browsePublicPostsResponse.getBody().as(PublicPostsModel[].class));
        int statusCode = browsePublicPostsResponse.statusCode();

        Assertions.assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        int two = 2;
    }
}

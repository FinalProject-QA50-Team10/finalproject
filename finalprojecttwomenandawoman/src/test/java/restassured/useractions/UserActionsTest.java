package restassured.useractions;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BaseTestSetupBeforeAfter;

import java.util.List;

public class UserActionsTest extends BaseTestSetupBeforeAfter {
    private final BaseSetupMethods userActionsAPI = new BaseSetupMethods();

    private List<Response> browsePublicPostsResponse;

    @Test
    public void browseAllPublicPosts()
    {
        browsePublicPostsResponse = userActionsAPI.browsePublicPosts();
    }
}

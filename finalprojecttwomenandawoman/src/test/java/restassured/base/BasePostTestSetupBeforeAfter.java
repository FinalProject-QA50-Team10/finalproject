package restassured.base;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;

public class BasePostTestSetupBeforeAfter {
    protected final BaseSetupMethods apiMethods = new BaseSetupMethods();

    protected final ApiTestAssertions assertions = new ApiTestAssertions();
    protected static int lastPostId;

}

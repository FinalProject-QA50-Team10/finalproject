package restassured.posts;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminPostEditDeleteTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Sign in with username MrBeast
    public void when_userSignsIn_expected_loginSuccessful() {
        Response signInWithUserMrBeast = posts.signInUser(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        assertions.assertStatusCode302(signInWithUserMrBeast.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expected_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent(createNewPublicPost, "Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");
    }

    @Test
    @Order(3)
    //FPT1-224 [Login Form] Login with a valid username and a valid password as an admin user
    public void when_adminUserSignsIn_expect_loginSuccessful() {
        Response signInWithUserAdmin = posts.signInUser(ADMIN_NAME, ADMIN_PASSWORD);
        assertions.assertStatusCode302(signInWithUserAdmin.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-60 [Edit Post]  Edit existing public post as Admin
    public void when_adminEditsPost_expect_postIsEdited() {
        Response editPostAsAdmin = posts.editPublicPost(ADMIN_NAME, ADMIN_PASSWORD, EDIT_POST_AS_ADMIN, lastPostId);
        assertions.assertStatusCode200(editPostAsAdmin.statusCode());
    }

    @Test
    @Order(5)
    //FPT1-61 [Delete Post] Delete an Existing Post as Admin
    public void when_adminDeletesPost_expect_postIsDeleted() {
        Response deletePostWithUserAdmin = posts.deletePost(ADMIN_NAME, ADMIN_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePostWithUserAdmin.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePostWithUserAdmin);
    }
}

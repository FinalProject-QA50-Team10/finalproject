package restassured.comments;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.PublicPostsModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicCommentInvalidCreationTest {

    private final BaseSetupMethods comments = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;
    private static int lastCommentId;

    @Test
    @Order(1)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expect_newPublicPostCreated() {
        Response createNewPublicPost = comments.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        var t = createNewPublicPost.as(PublicPostsModel.class);

        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent(t.content);
        assertions.assertPostIsPublic(createNewPublicPost);

        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);

        String content = createNewPublicPost.jsonPath().getString("content");
        assertions.assertContentIsExpected(content, "Valid Post");

        int categoryId = createNewPublicPost.jsonPath().getInt("category.id");
        assertions.assertCategoryIdIsExpected(categoryId, 102);

        String categoryName = createNewPublicPost.jsonPath().getString("category.name");
        assertions.assertCategoryNameIsExpected(categoryName, "Actor");

        lastPostId = postId;
    }

    @Test
    @Order(2)
    //FPT1-170 [Comment] Create Comment With 1001 Characters as Registered User
    public void when_userCreatesInvalidComment_expect_errorStatusCode() {
        String invalidComment = comments.generateInvalidComment();

        Response signInWithUserTomCruise = comments.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = comments.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, invalidComment, lastPostId);
        assertions.assertStatusCode400(createCommentResponse.statusCode());
    }

    @Test
    @Order(3)
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletesPost_expect_postIsDeleted() {
        Response deletePostResponse = comments.deletePost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePostResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePostResponse);
    }
}

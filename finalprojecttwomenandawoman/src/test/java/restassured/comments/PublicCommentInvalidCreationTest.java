package restassured.comments;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.telerikacademy.testframework.api.utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicCommentInvalidCreationTest {

    private final BaseSetupMethods posts = new BaseSetupMethods();
    private static int lastPostId;
    private static int lastCommentId;

    @Test
    @Order(1)
    //FPT1-85 [Login Page] Login with valid username and valid password - JackNicholson
    public void when_userSignsIn_expect_loginSuccessful() {
        Response signInWithUserJackNicholson = posts.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        posts.assertStatusCode302(signInWithUserJackNicholson.statusCode());
    }

    @Test
    @Order(2)
    //FPT1-25 [Add New Post] Generate new valid public post
    public void when_userSignsIn_expect_newPublicPostCreated() {
        Response createNewPublicPost = posts.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        posts.assertStatusCode200(createNewPublicPost.statusCode());
        posts.assertPostContent(createNewPublicPost, "Valid Post");
        posts.assertPostIsPublic(createNewPublicPost);

        int postId = createNewPublicPost.jsonPath().getInt("postId");
        posts.assertPostIdIsPositive(postId);

        String content = createNewPublicPost.jsonPath().getString("content");
        posts.assertContentIsExpected(content, "Valid Post");

        int categoryId = createNewPublicPost.jsonPath().getInt("category.id");
        posts.assertCategoryIdIsExpected(categoryId, 102);

        String categoryName = createNewPublicPost.jsonPath().getString("category.name");
        posts.assertCategoryNameIsExpected(categoryName, "Actor");

        lastPostId = postId;
    }

    @Test
    @Order(3)
    //FPT1-85 [Login Page] Login with valid username and valid password - TomCruise
    public void when_anotherUserSignsIn_expect_loginSuccessful() {
        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());
    }

    @Test
    @Order(4)
    //FPT1-170 [Comment] Create Comment With 1001 Characters as Registered User
    public void when_userCreatesInvalidComment_expect_errorStatusCode() {
        String invalidComment = posts.generateInvalidComment();

        Response signInWithUserTomCruise = posts.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        posts.assertStatusCode302(signInWithUserTomCruise.statusCode());

        Response createCommentResponse = posts.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, invalidComment, lastPostId);
        posts.assertStatusCode400(createCommentResponse.statusCode());
    }

    @Test
    @Order(5)
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_userDeletesPost_expect_postIsDeleted() {
        Response deletePostResponse = posts.deletePublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        posts.assertStatusCode200(deletePostResponse.statusCode());
        posts.assertResponseBodyIsEmpty(deletePostResponse);
    }
}
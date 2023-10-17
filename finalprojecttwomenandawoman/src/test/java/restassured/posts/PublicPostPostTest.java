package restassured.posts;

import com.telerikacademy.testframework.api.models.ErrorModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import restassured.base.BasePostTestSetupBeforeAfter;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class PublicPostPostTest extends BasePostTestSetupBeforeAfter {

    @Test
    //FPT1-25 [Add New Post] Create Public Post
    public void when_UserCreatesValidPublicPost_expect_ValidPublicPostIsCreated() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FTP1-35 [Add New Post] Generate new invalid public post
    public void when_UserCreatesInvalidPublicPost_expect_InvalidPublicPostIsNotCreated() {
        Response createNewInvalidPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_INVALID);
        var postModel = createNewInvalidPublicPost.as(ErrorModel.class);

        assertions.assertStatusCode400(createNewInvalidPublicPost.statusCode());
        assertions.assertBadRequest(postModel);
        assertions.assertInvalidPostContent(createNewInvalidPublicPost);
    }

    @Test
    //FTP1-45 [Edit Post] Edit existing public post
    public void when_UserEditsPublicPost_expect_PublicPostIsEdited() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response editPublicPost = apiMethods.editPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                EDIT_POST_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(editPublicPost.statusCode());

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }

    @Test
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void when_UserDeletsPublicPost_expect_PublicPostIsDeleted() {
        Response createNewPublicPost = apiMethods.createPublicPost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD,
                POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
        assertions.assertPostIsPublic(createNewPublicPost);
        int postId = createNewPublicPost.jsonPath().getInt("postId");
        assertions.assertPostIdIsPositive(postId);
        lastPostId = createNewPublicPost.jsonPath().getInt("postId");

        Response deletePublicPost = apiMethods.deletePost(MR_BEAST_USERNAME, MR_BEAST_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePublicPost.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePublicPost);
    }
}

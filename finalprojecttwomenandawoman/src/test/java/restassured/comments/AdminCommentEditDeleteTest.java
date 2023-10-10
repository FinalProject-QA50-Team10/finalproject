package restassured.comments;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.CommentModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class AdminCommentEditDeleteTest {
    private final BaseSetupMethods comments = new BaseSetupMethods();
    private final ApiTestAssertions assertions = new ApiTestAssertions();
    private static int lastPostId;
    private static int lastCommentId;

    @BeforeEach
    //FPT1-25 [Add New Post] Generate new valid public post
    //FPT1-167 [Comment] Create Comment Successfully as Registered User
    public void createPostAndComment() {

        //FPT1-25 [Add New Post] Generate new valid public post
        Response createNewPublicPost = comments.createPublicPost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, POST_DESCRIPTION_VALID);
        assertions.assertStatusCode200(createNewPublicPost.statusCode());
        assertions.assertPostContent("Valid Post");
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

        //FPT1-167 [Comment] Create Comment Successfully as Registered User
        Response signInWithUserTomCruise = comments.signInUser(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD);
        assertions.assertStatusCode302(signInWithUserTomCruise.statusCode());
        Response createCommentResponse = comments.createComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, COMMENT_DESCRIPTION_VALID, lastPostId);
        assertions.assertStatusCode200(createCommentResponse.statusCode());

        var t = createCommentResponse.as(CommentModel.class);
        String commentContent = t.content;
        assertions.assertContentIsExpected(commentContent, "Valid Comment");
        lastCommentId = createCommentResponse.jsonPath().getInt("commentId");
    }

    @AfterEach
    //FPT1-55 [Delete Post] Delete an Existing Public Post
    public void deletePost() {
        Response deletePostResponse = comments.deletePost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePostResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePostResponse);
    }

    @Test
    //FPT1-62 [Edit Comment] Edit an Existing Comment as Admin
    public void when_adminEditsComment_expect_commentIsEdited() {
        Response editCommentResponse = comments.editComment(ADMIN_NAME, ADMIN_PASSWORD, EDIT_COMMENT_CONTENT, lastCommentId);
        assertions.assertStatusCode200(editCommentResponse.statusCode());
    }

    @Test
    //FPT1-63 [Delete Comment] Delete an Existing Comment as Admin
    public void when_adminDeletesComment_expect_commentIsDeleted() {
        Response deleteCommentResponse = comments.deleteComment(ADMIN_NAME, ADMIN_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);
    }

}

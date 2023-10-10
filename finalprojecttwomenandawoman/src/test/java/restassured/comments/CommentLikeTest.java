package restassured.comments;

import com.telerikacademy.testframework.api.ApiTestAssertions;
import com.telerikacademy.testframework.api.BaseSetupMethods;
import com.telerikacademy.testframework.api.models.CommentModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.utils.Constants.*;

public class CommentLikeTest {

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
    //FPT1-182 [Comment] Delete Comment Successfully as Registered User
    public void deletePostAndComment() {
        Response deleteCommentResponse = comments.deleteComment(TOM_CRUISE_USERNAME, TOM_CRUISE_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(deleteCommentResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deleteCommentResponse);
        Response deletePostResponse = comments.deletePost(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastPostId);
        assertions.assertStatusCode200(deletePostResponse.statusCode());
        assertions.assertResponseBodyIsEmpty(deletePostResponse);
    }

    @Test
    //FPT1-125 [Like] Verify comments Like button
    //FPT1-126 [Like] Verify comments Dislike button
    public void when_userLikesComment_expect_commentIsLiked() {
        //FPT1-125 [Like] Verify comments Like button
        Response signInWithUserJackNicholson = comments.signInUser(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD);
        assertions.assertStatusCode302(signInWithUserJackNicholson.statusCode());

        Response likeCommentResponse = comments.likeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(likeCommentResponse.statusCode());
        assertions.assertCommentIsLiked(likeCommentResponse);

        //FPT1-126 [Like] Verify comments Dislike button
        Response dislikeCommentResponse = comments.dislikeComment(JACK_NICHOLSON_USERNAME, JACK_NICHOLSON_PASSWORD, lastCommentId);
        assertions.assertStatusCode200(dislikeCommentResponse.statusCode());
        assertions.assertStatusCode200(dislikeCommentResponse.statusCode());
        assertions.assertCommentIsDisliked(dislikeCommentResponse);
    }

}

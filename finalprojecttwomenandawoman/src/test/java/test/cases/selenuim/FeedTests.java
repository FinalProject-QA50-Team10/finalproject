package test.cases.selenuim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class FeedTests extends BaseTestSetup {

    @BeforeEach
    public void setup() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.createPost("public");
        authenticatedUserHomePage.logout();
    }

    @AfterEach
    public void teardown() {
        loginPage.navigateToPage();
        loginPage.login(MR_BEAST_USERNAME, MR_BEAST_PASSWORD);
        postPage.deletePost();
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-189 [Feed] Verify Unauthenticated Users Can Access Public Feed
    public void when_NavigatingToPublicFeedWithoutAuthentication_expect_FeedIsAccessible() {
        feedPage.navigateToPublicFeed();

        actions.waitForElementVisible(PUBLIC_FEED_TEXT);
        actions.assertElementPresent(PUBLIC_FEED_TEXT);

        actions.waitForElementVisible(EXPLORE_POST_BUTTON);
        actions.assertElementPresent(EXPLORE_POST_BUTTON);
    }
}

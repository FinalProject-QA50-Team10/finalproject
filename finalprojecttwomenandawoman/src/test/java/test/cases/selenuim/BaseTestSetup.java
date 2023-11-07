package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class BaseTestSetup {

    UserActions actions;
    LoginPage loginPage;
    UnauthenticatedUserHomePage unauthenticatedUser;
    RegistrationPage registerPage;
    SearchedUsersPage searchedUsersPage;
    RegisteredUserPage registeredUserPage;
    AuthenticatedUserHomePage authenticatedUserHomePage;
    PersonalProfilePage personalProfilePage;
    EditPersonalProfilePage editProfilePage;
    CommentPage commentPage;
    FeedPage feedPage;
    PostPage postPage;
    AdminHomePage adminHomePage;

    @BeforeEach
    public void setUp() {
        actions = new UserActions();

        loginPage = new LoginPage(actions.getDriver());
        unauthenticatedUser = new UnauthenticatedUserHomePage(actions.getDriver());
        registerPage = new RegistrationPage(actions.getDriver());
        searchedUsersPage = new SearchedUsersPage(actions.getDriver());
        registeredUserPage = new RegisteredUserPage(actions.getDriver());
        authenticatedUserHomePage = new AuthenticatedUserHomePage(actions.getDriver());
        personalProfilePage = new PersonalProfilePage(actions.getDriver());
        editProfilePage = new EditPersonalProfilePage(actions.getDriver());
        commentPage = new CommentPage(actions.getDriver());
        feedPage = new FeedPage(actions.getDriver());
        postPage = new PostPage(actions.getDriver());
        adminHomePage = new AdminHomePage(actions.getDriver());

        actions.loadBrowser(HOME_PAGE);
    }

    @AfterEach
    public void tearDown() {
        actions.quitDriver();
    }
}

package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.*;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.pages.Constants.HOME_BUTTON_PATH;
import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class BaseTestSetup {
    static UserActions actions = new UserActions();
    public LoginPage loginPage = new LoginPage(actions.getDriver());
    public UnauthenticatedUserHomePage unauthenticatedUser = new UnauthenticatedUserHomePage(actions.getDriver());
    public RegistrationPage registerPage = new RegistrationPage(actions.getDriver());
    public SearchedUsersPage searchedUsersPage = new SearchedUsersPage(actions.getDriver());
    public RegisteredUserPage registeredUserPage = new RegisteredUserPage(actions.getDriver());
    public AuthenticatedUserHomePage authenticatedUserHomePage = new AuthenticatedUserHomePage(actions.getDriver());
    public UserProfilePage userProfilePage = new UserProfilePage(actions.getDriver());
    public CommentPage commentPage = new CommentPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());
    AdminHomePage adminHomePage = new AdminHomePage(actions.getDriver());

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser(HOME_PAGE);
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }
}

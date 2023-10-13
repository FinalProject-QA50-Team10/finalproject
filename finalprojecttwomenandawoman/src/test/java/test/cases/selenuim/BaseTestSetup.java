package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.RegistrationPage;
import com.telerikacademy.testframework.pages.SearchedUsersPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class BaseTestSetup {
    static UserActions actions = new UserActions();
    public LoginPage loginPage = new LoginPage(actions.getDriver());
   public UnauthenticatedUserHomePage unauthenticatedUser = new UnauthenticatedUserHomePage(actions.getDriver());
   public RegistrationPage registerPage = new RegistrationPage(actions.getDriver());
    SearchedUsersPage searchedUsersPage = new SearchedUsersPage(actions.getDriver());

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser(HOME_PAGE);
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }
}

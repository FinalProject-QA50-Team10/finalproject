package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class BaseTestSetup {
    static UserActions actions = new UserActions();

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser(HOME_PAGE);
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }
}

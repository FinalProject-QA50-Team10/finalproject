package test.cases.selenuim;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;

public class BaseTestSetup {
    static UserActions actions = new UserActions();

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }
}

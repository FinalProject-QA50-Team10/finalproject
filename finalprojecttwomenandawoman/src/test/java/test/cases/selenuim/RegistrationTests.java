package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup{
    LoginPage loginPage = new LoginPage(actions.getDriver());
    UnauthenticatedUserHomePage unauthenticatedUser = new UnauthenticatedUserHomePage(actions.getDriver());

    @BeforeEach
    public void navigateToLoginPage()
    {
        unauthenticatedUser.clickRegisterButton();
        loginPage.navigateToPage();
    }
    @BeforeEach
    public void generateRandomUser(){
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
    }
    @Test
    public void test(){

    }
}

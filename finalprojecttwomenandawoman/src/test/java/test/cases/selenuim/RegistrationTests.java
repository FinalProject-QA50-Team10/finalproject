package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup{

    @BeforeEach
    public void navigateToLoginPage()
    {
        unauthenticatedUser.clickRegisterButton();
        registerPage.navigateToPage();
    }
    @BeforeEach
    public void generateRandomUser(){
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
    }
    @Test
    public void when_UnauthenticatedUserFillRegisterFormWithValidData_Expect_SuccessfulMessageIsVisible(){
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(RANDOM_USERNAME, RANDOM_EMAIL, GEORGE_BUSH_PASSWORD);
        actions.assertElementPresent(WELCOME_MESSAGE_PATH);
    }
}

package test.cases.selenuim;

import com.telerikacademy.testframework.pages.LoginPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup {

    @BeforeEach
    public void navigateToLoginPage() {
        unauthenticatedUser.clickRegisterButton();
        registerPage.navigateToPage();
    }

    @BeforeEach
    public void generateRandomUser() {
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
    }

    @Test
    //FPT1-135 [Registration] Successful Registration
    public void when_UnauthenticatedUserFillRegisterFormWithValidData_Expect_SuccessfulMessageIsVisible() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(RANDOM_USERNAME, RANDOM_EMAIL, GEORGE_BUSH_PASSWORD);
        registeredUserPage.assertWelcomeMessage();
        registeredUserPage.assertUpdateProfileButtonIsVisible();
        registeredUserPage.assertPageNavigated();
        authenticatedUserHomePage.logout();
    }

    @Test
    //FPT1-136 [Registration] Attempt Registration with Empty Fields
    public void when_UnauthenticatedUserFillRegisterFormWithEmptyData_Expect_Error() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        registerPage.assertPageNavigated();
    }

    @Test
    //FPT1-158 [Registration] Attempt Registration With Mismatched Passwords
    public void when_UnauthenticatedUserFillRegisterFormWithInvalidPassword_Expect_ErrorMessageIsVisible() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(RANDOM_USERNAME, RANDOM_EMAIL, INVALID_PASSWORD);
        registerPage.assertPageNavigated();
        registerPage.assertErrorMessageIsVisible();
        registerPage.assertErrorMessage(PASSWORD_ERROR_MESSAGE);
    }
    @Test
    //FPT1-162 [Registration] Attempt Registration With Email Containing Spaces
    public void when_UnauthenticatedUserFillRegisterFormWithInvalidEmail_Expect_ErrorMessageIsVisible() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(RANDOM_USERNAME, INVALID_EMAIL, MR_BEAST_PASSWORD);
        registerPage.assertPageNavigated();
        registerPage.assertErrorMessageIsVisible();
        registerPage.assertErrorMessage(EMAIL_ERROR_MESSAGE);
    }

    @Test
    //FPT1-143 [Registration] Attempt Registration With Already Existing Username
    public void when_UnauthenticatedUserFillRegisterFormWithAlreadyExistingUsername_Expect_ErrorMessageIsVisible() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(MR_BEAST_USERNAME, RANDOM_EMAIL, MR_BEAST_PASSWORD);
        registerPage.assertPageNavigated();
        registerPage.assertErrorMessageIsVisible();
        registerPage.assertErrorMessage(EXISTING_USERNAME_ERROR_MESSAGE);
    }
}

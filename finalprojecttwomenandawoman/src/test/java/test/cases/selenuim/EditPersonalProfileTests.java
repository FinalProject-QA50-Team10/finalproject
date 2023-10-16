package test.cases.selenuim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class EditPersonalProfileTests extends BaseTestSetup {

    @BeforeEach
    public void login() {
        unauthenticatedUser.clickSignInButton();
        loginPage.login(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        authenticatedUserHomePage.clickPersonalProfileButton();
    }

    @AfterEach
    public void logoutAndNavigateToHomePage() {
        editProfilePage.clickLogoutButton();
        loginPage.navigateToHomePage();
    }

    @Test
    //[Edit Profile] Edit User Profile with Personal Information
    public void when_AuthenticatedUserClickEditProfileButton_Expect_PersonalProfileFormIsVisible() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickEditProfileButton();
        editProfilePage.assertPageNavigated();
        editProfilePage.assertPersonalProfileFormTitle();
    }

    @Test
    //[Edit Profile] Edit User Profile with Personal Information
    public void when_AuthenticatedUserEditPersonalProfileWithValidNewNameAndValidNewEmail_Expect_PersonalProfileIsUpdated() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickEditProfileButton();
        editProfilePage.assertPageNavigated();
        editProfilePage.fillEditPersonalProfile(
                FOR_EDIT_FIRST_NAME, FOR_EDIT_LAST_NAME, FOR_EDIT_EMAIL);
        editProfilePage.assertPageNavigated();
        editProfilePage.clickPersonalProfileButton();
        editProfilePage.asserUserNameEdited(FOR_EDIT_FIRST_NAME, FOR_EDIT_LAST_NAME);
        editProfilePage.assertEmailEdited(FOR_EDIT_EMAIL);
    }

    @Test
    //[Edit Profile] Edit User Profile with Empty Data in First Name and Verify Error Handling
    public void when_AuthenticatedUserEditPersonalProfileWithEmptyFirstName_Expect_ErrorMessageIsVisible() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickEditProfileButton();
        editProfilePage.assertPageNavigated();
        editProfilePage.fillEditPersonalProfile(
                EMPTY_STRING, FOR_EDIT_LAST_NAME, FOR_EDIT_EMAIL);
        editProfilePage.assertErrorMessage();
    }
}

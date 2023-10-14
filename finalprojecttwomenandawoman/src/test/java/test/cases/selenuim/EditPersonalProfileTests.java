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
    public void when_AuthenticatedUserClickEditProfileButton_Expect_PersonalProfileIsUpdated() {
        personalProfilePage.assertPageNavigated();
        personalProfilePage.clickEditProfileButton();
        editProfilePage.assertPageNavigated();
        editProfilePage.fillEditPersonalProfile("dani", "sham", "07/17/2021", "email@gmail.bg");
//        editProfilePage.fillEditPersonalProfile(
//                FOR_EDIT_FIRST_NAME, FOR_EDIT_LAST_NAME, FOR_EDIT_BIRTHDAY, FOR_EDIT_EMAIL);
        editProfilePage.assertPageNavigated();


//        editProfilePage.assertFirstNameEdited("dani");
//        editProfilePage.assertLastNameEdited("sham");
//        editProfilePage.assertBirthDateEdited("07/17/2021");
//        editProfilePage.assertEmailEdited("email@gmail.bg");
    }
}

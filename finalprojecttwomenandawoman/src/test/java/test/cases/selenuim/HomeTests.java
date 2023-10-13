package test.cases.selenuim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;


public class HomeTests extends BaseTestSetup {

    @AfterEach
    public void clickHomeButton() {
        unauthenticatedUser.navigateToHomePage();
    }

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_NavigateToHomePage_Expect_SearchFormIsVisible() {
        unauthenticatedUser.navigateToPage();
        unauthenticatedUser.assertSearchFormIsVisible();
        unauthenticatedUser.assertSearchFormAttributes();
    }

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_unauthenticatedUserSearchUsersByValidJobTitle_Expect_SearchedUsersAreVisible() {
        unauthenticatedUser.searchForm(VALID_JOB_TITLE, EMPTY_STRING);
        searchedUsersPage.assertUsersAreVisible();
        searchedUsersPage.assertSearchResultsAreVisible();
        searchedUsersPage.assertUsersAttributes(VALID_JOB_TITLE, SEARCH_RESULTS_JOB_TITLE_PATH);
    }

    @Test
    //[Search Form] Search users with a valid first and valid second name as a guest
    public void when_unauthenticatedUserSearchUsersByValidName_Expect_SearchedUsersAreVisible() {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        searchedUsersPage.assertSearchResultsAreVisible();
        searchedUsersPage.assertUsersAreVisible();
        searchedUsersPage.assertUsersAttributes(GEORGE_BUSH_NAME, SEARCH_RESULTS_NAME_PATH);
    }

    @Test
    //[Search Form] Search users with a valid first name as a guest
    public void when_unauthenticatedUserSearchUsersByValidFirstName_Expect_SearchedUsersAreVisible() {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_FIRST_NAME);
        searchedUsersPage.assertSearchResultsAreVisible();
        searchedUsersPage.assertUsersAreVisible();
        searchedUsersPage.assertUsersAttributes(GEORGE_BUSH_FIRST_NAME, SEARCH_RESULTS_NAME_PATH);
    }

    @Test
    //[Search Form] Search users with a valid last name as a guest
    public void when_unauthenticatedUserSearchUsersByValidLastName_Expect_SearchedUsersAreVisible() {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_LAST_NAME);
        searchedUsersPage.assertSearchResultsAreVisible();
        searchedUsersPage.assertUsersAreVisible();
        searchedUsersPage.assertUsersAttributes(GEORGE_BUSH_LAST_NAME, SEARCH_RESULTS_NAME_PATH);
    }

    @Test
    //[Login Page] Verify Sing in button functionality and Login page elements
    public void when_unauthenticatedUserClickOnSignInButton_expect_successfulLoginPageElementsAreVisible() {
        unauthenticatedUser.clickSignInButton();
        loginPage.assertLoginPageTitle();
        loginPage.assertLoginButtonVisible();
        loginPage.assertUsernameFieldVisible();
        loginPage.assertPasswordFieldVisible();
    }
}

package test.cases.selenuim;

import com.telerikacademy.testframework.pages.SearchedUsersPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;


public class HomeTests extends BaseTestSetup {

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_NavigateToHomePage_Expect_SearchFormIsVisible(){
        unauthenticatedUser.navigateToPage();
        actions.assertElementPresent(SEARCH_FORM_PATH);
    }

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_unauthenticatedUserSearchUsersByValidJobTitle_Expect_SearchedUsersAreVisible()
    {
        unauthenticatedUser.searchForm(VALID_JOB_TITLE, EMPTY_STRING);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertUsersAttribute(VALID_JOB_TITLE, SEARCH_RESULTS_JOB_TITLE_PATH);

    }

    @Test
    //[Search Form] Search users with a valid first and valid second name as a guest
    public void when_unauthenticatedUserSearchUsersByValidName_Expect_SearchedUsersAreVisible()
    {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertUsersAttribute(GEORGE_BUSH_NAME, SEARCH_RESULTS_NAME_PATH);

    }

    @Test
    //[Search Form] Search users with a valid first name as a guest
    public void when_unauthenticatedUserSearchUsersByValidFirstName_Expect_SearchedUsersAreVisible()
    {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_FIRST_NAME);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertSearchedUsersContainsName(GEORGE_BUSH_FIRST_NAME, SEARCH_RESULTS_NAME_PATH);
    }

    @Test
    //[Search Form] Search users with a valid last name as a guest
    public void when_unauthenticatedUserSearchUsersByValidLastName_Expect_SearchedUsersAreVisible()
    {
        unauthenticatedUser.searchForm(EMPTY_STRING, GEORGE_BUSH_LAST_NAME);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertSearchedUsersContainsName(GEORGE_BUSH_LAST_NAME, SEARCH_RESULTS_NAME_PATH);
    }
    @Test
    //[Login Page] Verify Sing in button functionality and Login page elements
    public void when_unauthenticatedUserClickOnSignInButton_expect_successfulLoginPageElementsAreVisible()
    {
        unauthenticatedUser.clickSignInButton();
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
    }
}

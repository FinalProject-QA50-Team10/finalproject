package test.cases.selenuim;

import com.telerikacademy.testframework.pages.SearchedUsersPage;
import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;


public class HomeTests extends BaseTestSetup {

    UnauthenticatedUserHomePage home = new UnauthenticatedUserHomePage(actions.getDriver());

    SearchedUsersPage searchedUsersPage = new SearchedUsersPage(actions.getDriver());

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_NavigateToHomePage_Expect_SearchFormIsVisible(){
        home.navigateToPage();
        actions.assertElementPresent(SEARCH_FORM_PATH);
    }

    @Test
    //[Search Form] Search users by valid job title as a guest
    public void when_unauthenticatedUserSearchUsersByValidJobTitle_Expect_SearchedUsersAreVisible()
    {
        home.searchForm(VALID_JOB_TITLE, EMPTY_STRING);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertUsersAttribute(VALID_JOB_TITLE, SEARCH_RESULTS_JOB_TITLE_PATH);

    }

    @Test
    //[Search Form] Search users with a valid first and valid second name as a guest
    public void when_unauthenticatedUserSearchUsersByValidName_Expect_SearchedUsersAreVisible()
    {
        home.searchForm(EMPTY_STRING, GEORGE_BUSH_NAME);
        actions.assertElementPresent(SEARCH_RESULTS_PATH);
        actions.assertUsersListIsNotEmpty(SEARCH_RESULTS_PATH);
        actions.assertUsersAttribute(GEORGE_BUSH_NAME, SEARCH_RESULTS_NAME_PATH);

    }
    @Test
    //[Login Page] Verify Sing in button functionality and Login page elements
    public void when_unauthenticatedUserClickOnSignInButton_expect_successfulLoginPageElementsAreVisible()
    {
        home.clickSignInButton();
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
    }
}

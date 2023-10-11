package test.cases.selenuim;

import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;


public class HomeTests extends BaseTestSetup {

    UnauthenticatedUserHomePage home = new UnauthenticatedUserHomePage(actions.getDriver());
    @Test
    public void when_navigateToHomePage_expect_searchFormIsVisible(){
        home.navigateToPage();
        actions.assertElementPresent(SEARCH_FORM_PATH);
    }

    @Test
    public void when_unauthenticatedUserClickOnSignInButton_expect_successfulLoginPageElementsAreVisible()
    {
        home.clickSignInButton();
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(PERSONAL_PROFILE_BUTTON_PATH);
        actions.assertElementPresent(ADD_NEW_POST_BUTTON_PATH);
    }
}

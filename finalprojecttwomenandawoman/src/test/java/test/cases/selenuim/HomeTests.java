package test.cases.selenuim;

import com.telerikacademy.testframework.pages.UnauthenticatedUserHomePage;
import org.junit.jupiter.api.Test;


public class HomeTests extends BaseTestSetup {

    UnauthenticatedUserHomePage home = new UnauthenticatedUserHomePage(actions.getDriver());
    @Test
    public void when_navigateToHomePage_expect_searchFormIsVisible(){
        home.navigateToPage();
        home.assertSearchFormVisible();
    }
}

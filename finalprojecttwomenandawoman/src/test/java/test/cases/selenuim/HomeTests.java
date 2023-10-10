package test.cases.selenuim;

import com.telerikacademy.testframework.pages.HomePage;
import org.junit.jupiter.api.Test;


public class HomeTests extends BaseTestSetup {

    @Test
    public void when_navigateToHomePage_expect_searchFormIsVisible(){
        HomePage home = new HomePage(actions.getDriver());
        home.navigateToPage();
        home.assertSearchFormVisible();
    }
}

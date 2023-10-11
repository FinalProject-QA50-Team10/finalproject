package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }

    public void login(String username, String password){
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), LOGIN_USERNAME_FIELD);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), LOGIN_PASSWORD_FIELD);
        actions.clickElement(LOGIN_BUTTON);
    }


}

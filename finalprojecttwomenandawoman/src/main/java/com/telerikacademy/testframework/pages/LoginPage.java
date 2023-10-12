package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }

    public void login(String username, String password){
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), LOGIN_USERNAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), LOGIN_PASSWORD_FIELD_PATH);
        actions.clickElement(LOGIN_BUTTON_PATH);
    }

    public void navigateToHomePage(){
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    public void logout(){
        actions.clickElement(LOGOUT_BUTTON_PATH);
    }
}

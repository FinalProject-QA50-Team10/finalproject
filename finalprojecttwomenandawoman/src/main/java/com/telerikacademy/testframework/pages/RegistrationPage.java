package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void successfulRegistration() {

    }

    public void attemptRegistrationWithEmptyFields() {

    }

    public void attemptRegistrationWithCyrillicCharactersInUsername() {

    }

    public void attemptRegistrationWithEmptyPasswordField() {

    }

    public void attemptRegistrationWithEmailContainingSpaces() {

    }


}

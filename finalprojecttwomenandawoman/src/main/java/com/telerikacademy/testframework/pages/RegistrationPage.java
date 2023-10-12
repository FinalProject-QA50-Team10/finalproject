package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;
import static com.telerikacademy.testframework.pages.Constants.REGISTER_PAGE;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver, REGISTER_PAGE);
    }


}

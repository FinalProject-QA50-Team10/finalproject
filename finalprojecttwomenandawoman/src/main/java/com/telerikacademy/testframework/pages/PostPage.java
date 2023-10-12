package com.telerikacademy.testframework.pages;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.ADMIN_ZONE_BUTTON;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public void createPublicPost() {

    }
}

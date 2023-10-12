package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Objects;

import static com.telerikacademy.testframework.pages.Constants.*;

public class SearchedUsersPage extends BasePage{

    public SearchedUsersPage(WebDriver driver) {
        super(driver, SEARCHED_USERS_PAGE);
    }


}

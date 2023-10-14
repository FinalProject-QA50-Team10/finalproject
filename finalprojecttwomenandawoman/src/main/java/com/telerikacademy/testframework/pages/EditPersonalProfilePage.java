package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class EditPersonalProfilePage extends BasePage {
    public EditPersonalProfilePage(WebDriver driver) {
        super(driver, PERSONAL_PROFILE_PAGE);
    }

    public void fillEditPersonalProfile(String firstName, String lastName, String birthday, String email) {
        actions.waitForElementClickable(EDIT_FIRST_NAME_PATH);
        actions.clearValueInField(EDIT_FIRST_NAME_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(firstName), EDIT_FIRST_NAME_PATH);
        actions.waitForElementClickable(EDIT_LAST_NAME_PATH);
        actions.clearValueInField(EDIT_LAST_NAME_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(lastName), EDIT_LAST_NAME_PATH);
        actions.waitForElementClickable(EDIT_BIRTH_DATE_PATH);
        actions.clearValueInField(EDIT_BIRTH_DATE_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(birthday), EDIT_BIRTH_DATE_PATH);
        actions.waitForElementClickable(EDIT_EMAIL_PATH);
        actions.clearValueInField(EDIT_EMAIL_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(email), EDIT_EMAIL_PATH);
        actions.waitForElementClickable(EDIT_PROFILE_CHANGES_BUTTON_PATH);
        actions.clickElement(EDIT_PROFILE_CHANGES_BUTTON_PATH);
    }

    public void clickLogoutButton() {
        actions.waitForElementClickable(LOGOUT_BUTTON_PATH);
        actions.clickElement(LOGOUT_BUTTON_PATH);
    }

    @Override
    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(Utils.getConfigPropertyByKey(PERSONAL_PROFILE_PAGE)),
                "Landed URL is not as expected. Actual URL: " + currentUrl +
                        ". Expected URL: " + PERSONAL_PROFILE_PAGE);
    }

    public void assertPersonalProfileFormTitle() {
        String actualTitle = actions.getElement(EDIT_PROFILE_FORM_TITLE_PATH).getText();
        Assertions.assertEquals(EDIT_PROFILE_FORM_TITLE, actualTitle,
                String.format("Expected edit profile form title is different than actual. Expected: %s; Actual: %s.",
                        EDIT_PROFILE_FORM_TITLE, actualTitle));
    }

    public void assertFirstNameEdited(String expectedFirstName) {
        actions.waitForElementVisible(EDIT_FIRST_NAME_PATH);
        var actualFirstName = actions.getElement(EDIT_FIRST_NAME_PATH).getText();
        Assertions.assertEquals(expectedFirstName, actualFirstName,
                "Expected first name is different than actual.");
    }

    public void assertLastNameEdited(String expectedLastName) {
        actions.waitForElementVisible(EDIT_LAST_NAME_PATH);
        var actualLastName = actions.getElement(EDIT_LAST_NAME_PATH).getText();
        Assertions.assertEquals(expectedLastName, actualLastName,
                "Expected last name is different than actual.");
    }

    public void assertBirthDateEdited(String expectedBirthday) {
        actions.waitForElementVisible(EDIT_BIRTH_DATE_PATH);
        Assertions.assertEquals(expectedBirthday, EDIT_BIRTH_DATE_PATH,
                "Expected birthday is different than actual.");
    }

    public void assertEmailEdited(String expectedEmail) {
        actions.waitForElementVisible(EDIT_EMAIL_PATH);
        var actualEmail = actions.getElement(EDIT_EMAIL_PATH).getText();
        Assertions.assertEquals(expectedEmail, actualEmail,
                "Expected email is different than actual.");
    }
}

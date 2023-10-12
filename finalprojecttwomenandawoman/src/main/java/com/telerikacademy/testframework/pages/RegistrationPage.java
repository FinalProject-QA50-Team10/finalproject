package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.ThreadLocalRandom;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver, HOME_PAGE);
    }

    public static String generateRandomText(int length) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder randomText = new StringBuilder(length);

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            char randomChar = alphabet[random.nextInt(alphabet.length)];
            randomText.append(randomChar);
        }

        return randomText.toString();
    }
}

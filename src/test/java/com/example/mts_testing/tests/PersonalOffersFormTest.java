package com.example.mts_testing.tests;

import com.example.mts_testing.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonalOffersFormTest extends BaseTest {
    private final static String BASE_URL = "https://www.mtsbank.ru";
    private final static String phoneNumber = "9000000000";

    @Test
    public void testIfSubmitButtonIsVisibleNotFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickFindOutButton();
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertFalse(result);
    }

    @Test
    public void testIfSubmitButtonIsVisibleFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.enterPhoneNumber(phoneNumber);
        mainPage.clickFindOutButton();
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertTrue(result);
    }
}

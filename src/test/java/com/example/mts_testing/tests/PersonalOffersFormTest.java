package com.example.mts_testing.tests;

import com.codeborne.selenide.Selenide;
import com.example.mts_testing.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonalOffersFormTest extends BaseTest {
    private final static String BASE_URL = "https://www.mtsbank.ru";
    private final static String phoneNumber = "9000000004";

    @Test
    public void testIfSubmitButtonIsVisibleNotFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL, false);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertFalse(result);
    }

    @Test
    public void testIfSubmitButtonIsVisibleFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL, false);
        mainPage.enterPhoneNumber(phoneNumber);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertTrue(result);
    }

    @Test
    public void testIfSubmitButtonIsVisibleNotFillingPhoneNumberForMobileVersion() {
        MainPage mainPage = new MainPage(BASE_URL, true);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertFalse(result);
    }

    @Test
    public void testIfSubmitButtonIsVisibleFillingPhoneNumberForMobileVersion() {
        MainPage mainPage = new MainPage(BASE_URL, true);
        mainPage.enterPhoneNumber(phoneNumber);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertTrue(result);
    }

}

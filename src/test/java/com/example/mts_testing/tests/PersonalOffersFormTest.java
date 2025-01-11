package com.example.mts_testing.tests;

import com.codeborne.selenide.Selenide;
import com.example.mts_testing.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Этот класс реализует тестирование заполнения формы на странице https://www.mtsbank.ru, проверяя
 * появляется ли на странице кнопка "Подтвердить" после заполнения номера телефона и отправки СМС. Класс является потомком BaseTest.java */
public class PersonalOffersFormTest extends BaseTest {

    /**Ссылка, которую нужно открыть в браузере*/
    private final static String BASE_URL = "https://www.mtsbank.ru";

    /** Этот метод реализует тестирование заполнения формы на странице https://www.mtsbank.ru, проверяя
     * появляется ли на странице кнопка "Подтвердить", если отправить пустую форму. */
    @Test
    public void testIfSubmitButtonIsVisibleNotFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL, false);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertFalse(result);
    }
    /** Этот метод реализует тестирование заполнения формы на странице https://www.mtsbank.ru, проверяя
    * появляется ли на странице кнопка "Подтвердить" после заполнения номера телефона и отправки СМС.*/
    @Test
    public void testIfSubmitButtonIsVisibleFillingPhoneNumber() {
        MainPage mainPage = new MainPage(BASE_URL, false);
        mainPage.enterPhoneNumber(generateRandomTenDigitNumberAsString());
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertTrue(result);
    }

    /** Этот метод реализует тестирование мобильной версии сайта: заполнение формы на странице https://www.mtsbank.ru, проверяя
     * появляется ли на странице кнопка "Подтвердить", если отправить пустую форму. */
    @Test
    public void testIfSubmitButtonIsVisibleNotFillingPhoneNumberForMobileVersion() {
        MainPage mainPage = new MainPage(BASE_URL, true);
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertFalse(result);
    }

    /** Этот метод реализует тестирование мобильной версии сайта: заполнение формы на странице https://www.mtsbank.ru, проверяя
     * появляется ли на странице кнопка "Подтвердить" после заполнения номера телефона и отправки СМС.*/
    @Test
    public void testIfSubmitButtonIsVisibleFillingPhoneNumberForMobileVersion() {
        MainPage mainPage = new MainPage(BASE_URL, true);
        mainPage.enterPhoneNumber(generateRandomTenDigitNumberAsString());
        mainPage.clickFindOutButton();
        Selenide.sleep(5000);
        boolean result = mainPage.searchSubmitButton();
        Assertions.assertTrue(result);
    }

    /**Метод генерации случайных номеров телефона для заполнения формы
     * @return десятизначное число в формате String*/
    public static String generateRandomTenDigitNumberAsString() {
        double randomNumber = Math.random();
        long number = (long) (randomNumber * 10_000_000_000L);
        return String.format("%010d", number);
    }
}

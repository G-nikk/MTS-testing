package com.example.mts_testing.tests;

import com.example.mts_testing.pages.DebetCardsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Этот класс реализует тестирование заполнения формы на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/, сравнивая
 * ожидаемый процент ее заполненности с текущим. Класс является потомком BaseTest.java */
public class FormFillingTest extends BaseTest {

    /**Ссылка, которую нужно открыть в браузере*/
    private final static String BASE_URL = "https://www.mtsbank.ru/chastnim-licam/karti/all/debet/";

    /**Номер телефона, вводимый в форму*/
    private final static String phoneNumber = "9013870552";

    /**ФИО, вводимое в форму*/
    private final static String name = "Иванов Иван Иванович";

    /**Почта, вводимая в форму*/
    private final static String email = "test@mail.ru";

    /**Этот тест открывает страницу и заполняет форму, проверяя совпадение ожидаемого и текущего процента
     * её заполненности*/
    @Test
    public void testFormFilling() {
        DebetCardsPage page = new DebetCardsPage(BASE_URL, false);
        boolean result = page.fillDataAndCheckPercent(phoneNumber, name, email);
        Assertions.assertTrue(result);
    }

    /**Этот тест открывает страницу в мобильной версии и заполняет форму, проверяя совпадение ожидаемого и текущего процента
     * её заполненности*/
    @Test
    public void testFormFillingForMobileVersion() {
        DebetCardsPage page = new DebetCardsPage(BASE_URL, true);
        boolean result = page.fillDataAndCheckPercent(phoneNumber, name, email);
        Assertions.assertTrue(result);
    }
}

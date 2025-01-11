package com.example.mts_testing.tests;

import com.example.mts_testing.pages.DebetCardsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormFillingTest extends BaseTest {
    private final static String BASE_URL = "https://www.mtsbank.ru";
    private final static String phoneNumber = "9013870552";
    private final static String name = "Иванов Иван Иванович";
    private final static String email = "test@mail.ru";

    @Test
    public void testFormFilling() {
        DebetCardsPage page = new DebetCardsPage(BASE_URL, false);
        boolean result = page.fillData(phoneNumber, name, email);
        Assertions.assertTrue(result);
    }

    @Test
    public void testFormFillingForMobileVersion() {
        DebetCardsPage page = new DebetCardsPage(BASE_URL, true);
        boolean result = page.fillData(phoneNumber, name, email);
        Assertions.assertTrue(result);
    }
}

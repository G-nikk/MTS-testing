package com.example.mts_testing.tests;

import com.example.mts_testing.pages.MortgageCalculatorPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/** Этот класс реализует тестирование механизма расчёта емесячного ипотечного платежа в зависимости от количества лет на странице
 * https://www.mtsbank.ru/chastnim-licam/ipoteka/, сравнивая текущую сумму платежа с предыдущей. С увеличением количесва
 * лет и коррекных вычислениях, сумма платежа должна уменьшаться.
 * Класс является потомком BaseTest.java */
public class PaymentsCalculationTest extends BaseTest {

    /**Ссылка, которую нужно открыть в браузере*/
    private final static String BASE_URL = "https://www.mtsbank.ru/chastnim-licam/ipoteka/";

    /**Этот тест открывает страницу и реализует тестирование механизма расчёта емесячного ипотечного платежа в зависимости от количества лет на странице
     * https://www.mtsbank.ru/chastnim-licam/ipoteka/, сравнивая текущую сумму платежа с предыдущей. С увеличением количесва
     * лет и коррекных вычислениях, сумма платежа должна уменьшаться.*/
    @Test
    public void testPaymentsCalculation() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, false);
        boolean result = mortgageCalculatorPage.fillDataAndComparePayments();
        Assertions.assertTrue(result);
    }

    /**Этот тест открывает страницу и реализует тестирование механизма расчёта емесячного ипотечного платежа в зависимости от количества лет на странице
     * https://www.mtsbank.ru/chastnim-licam/ipoteka/, вводя некорректное количество лет. Однако сайт все равно вычисляет ежемясячный платёж,
     * что является багом этой системы*/
    @Test
    public void testPaymentsCalculationWithWrongData() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, false);
        boolean result = mortgageCalculatorPage.fillWrongData();
        Assertions.assertTrue(result);
    }

    /**Этот тест открывает страницу мобильной версии браузера и реализует тестирование механизма расчёта емесячного ипотечного платежа в зависимости от количества лет на странице
     * https://www.mtsbank.ru/chastnim-licam/ipoteka/, сравнивая текущую сумму платежа с предыдущей. С увеличением количесва
     * лет и коррекных вычислениях, сумма платежа должна уменьшаться.*/
    @Test
    public void testPaymentsCalculationForMobileVersion() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, true);
        boolean result = mortgageCalculatorPage.fillDataAndComparePayments();
        Assertions.assertTrue(result);
    }

    /**Этот тест открывает страницу в мобильной версии браузера и реализует тестирование механизма расчёта емесячного ипотечного платежа в зависимости от количества лет на странице
     * https://www.mtsbank.ru/chastnim-licam/ipoteka/, вводя некорректное количество лет. Однако сайт все равно вычисляет ежемясячный платёж,
     * что является багом этой системы*/
    @Test
    public void testPaymentsCalculationWithWrongDataForMobileVersion() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, true);
        boolean result = mortgageCalculatorPage.fillWrongData();
        Assertions.assertTrue(result);
    }
}

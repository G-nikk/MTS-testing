package com.example.mts_testing.tests;

import com.example.mts_testing.pages.MortgageCalculatorPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PaymentsCalculationTest extends BaseTest {
    private final static String BASE_URL = "https://www.mtsbank.ru/chastnim-licam/ipoteka/";

    @Test
    public void testPaymentsCalculation() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, false);
        boolean result = mortgageCalculatorPage.fillDataAndComparePayments();
        Assertions.assertTrue(result);
    }

    @Test
    public void testPaymentsCalculationWithWrongData() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, false);
        boolean result = mortgageCalculatorPage.fillWrongData();
        Assertions.assertTrue(result);
    }

    @Test
    public void testPaymentsCalculationForMobileVersion() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, true);
        boolean result = mortgageCalculatorPage.fillDataAndComparePayments();
        Assertions.assertTrue(result);
    }

    @Test
    public void testPaymentsCalculationWithWrongDataForMobileVersion() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL, true);
        boolean result = mortgageCalculatorPage.fillWrongData();
        Assertions.assertTrue(result);
    }
}

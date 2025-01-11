package com.example.mts_testing.tests;

import com.example.mts_testing.pages.MortgageCalculatorPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PaymentsCalculationTest extends BaseTest {
    private final static String BASE_URL = "https://www.mtsbank.ru";

    @Test
    public void testPaymentsCalculation() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL);
        boolean result = mortgageCalculatorPage.fillDataAndComparePayments();
        Assertions.assertTrue(result);
    }

    @Test
    public void testPaymentsCalculationWithWrongData() {
        MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage(BASE_URL);
        boolean result = mortgageCalculatorPage.fillWrongData();
        Assertions.assertTrue(result);
    }
}

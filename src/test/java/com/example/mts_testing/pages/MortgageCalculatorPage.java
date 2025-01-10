package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class MortgageCalculatorPage {
    private final SelenideElement mortgageButton = $x("//a[@href='/chastnim-licam/ipoteka/' and @class='LinkWrapper-sc-f1cacf38-0 jeazkY sc-5055b9e7-5 fYIeVB']");
    private final SelenideElement calculatorButton = $x("//div[text()='Калькулятор']");
    private final SelenideElement yearsInputField = $x("//div[@label='Срок кредита']/child::input");
    private final SelenideElement currentPayment = $x("//div[text()='Ежемес. платеж']/following-sibling::h4[@class='Wrapper-sc-6nwvzq-0 ckobOi']");
    private final SelenideElement minYears = $x("//div[text()='от 3 лет']");
    private final SelenideElement maxYears = $x("//div[text()='до 30 лет']");

    public MortgageCalculatorPage(String url){
        Selenide.open(url);
    }

    public MortgageCalculatorPage goToMortgageCalculatorPage(){
        mortgageButton.click();
        calculatorButton.click();
        return this;
    }

    public boolean fillDataAndComparePayments() {
        int min = Integer.parseInt(minYears.getText().replaceAll("[^0-9]", ""));
        int max = Integer.parseInt(maxYears.getText().replaceAll("[^0-9]", ""));
        int previousSum = Integer.parseInt(currentPayment.getText().replaceAll("[^0-9]", ""));
        for (int i = min; i <= max; i++) {
            yearsInputField.click();
            yearsInputField.sendKeys(Keys.BACK_SPACE);
            yearsInputField.sendKeys(Keys.BACK_SPACE);
            yearsInputField.setValue(String.valueOf(i)).pressEnter();
            Selenide.sleep(1000);
            int currentSum = Integer.parseInt(currentPayment.getText().replaceAll("[^0-9]", ""));
            if (previousSum < currentSum && i != min) {
                return false;
            }
            previousSum = currentSum;
        }
        return true;
    }
}

package com.example.mts_testing.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class MortgageCalculatorPage {
    private final SelenideElement mortgageButton = $x("//a[@href='/chastnim-licam/ipoteka/' and @class='LinkWrapper-sc-f1cacf38-0 jeazkY sc-5055b9e7-5 fYIeVB']");
    private final SelenideElement calculatorButton = $x("//div[text()='Калькулятор']");
    private final SelenideElement yearsInputField = $x("//div[@label='Срок кредита']/child::input");
    private final SelenideElement currentPayment = $x("//div[text()='Ежемес. платеж']/following-sibling::h4[@class='Wrapper-sc-6nwvzq-0 ckobOi']");
    private final SelenideElement currentPaymentMobile = $x("//div[text()='Платеж']/following-sibling::div");
    private final SelenideElement minYears = $x("//div[text()='от 3 лет']");
    private final SelenideElement maxYears = $x("//div[text()='до 30 лет']");
    private final SelenideElement areaToHover = $x("//div[@class='LinksWrapper-eerdxp-0 icydKH']");
    private final SelenideElement firstPayment = $x("//div[text()='Первоначальный взнос']");

    public MortgageCalculatorPage(String url, boolean mobileVersion){
        if (mobileVersion){
            Configuration.browserSize = "390x844";
        }
        else Configuration.browserSize = "1143x739";
        Selenide.open(url);
        Selenide.sleep(1000);
        mortgageButton.click();
        if (areaToHover.isDisplayed()) areaToHover.hover();
        calculatorButton.click();
        firstPayment.scrollIntoView(true);
    }

    public boolean fillDataAndComparePayments() {
        int min = Integer.parseInt(minYears.getText().replaceAll("[^0-9]", ""));
        int max = Integer.parseInt(maxYears.getText().replaceAll("[^0-9]", ""));
        int previousSum;
        if (currentPaymentMobile.isDisplayed()) {
            previousSum = Integer.parseInt(currentPaymentMobile.getText().replaceAll("[^0-9]", ""));
        }
        else {
            previousSum = Integer.parseInt(currentPayment.getText().replaceAll("[^0-9]", ""));
        }
        for (int i = min; i <= max; i++) {
            yearsInputField.click();
            yearsInputField.sendKeys(Keys.BACK_SPACE);
            yearsInputField.sendKeys(Keys.BACK_SPACE);
            yearsInputField.setValue(String.valueOf(i)).pressEnter();
            Selenide.sleep(1000);
            int currentSum;
            if (currentPaymentMobile.isDisplayed()) {
                currentSum = Integer.parseInt(currentPaymentMobile.getText().replaceAll("[^0-9]", ""));
            }
            else {
                currentSum = Integer.parseInt(currentPayment.getText().replaceAll("[^0-9]", ""));
            }
            if (previousSum < currentSum && i != min) {
                return false;
            }
            previousSum = currentSum;
        }
        return true;
    }

    public boolean fillWrongData() {
        int min = Integer.parseInt(minYears.getText().replaceAll("[^0-9]", ""));
        int max = Integer.parseInt(maxYears.getText().replaceAll("[^0-9]", ""));
        return fillYearsInputFieldAndCheckCurrentPayment(min - 1) && fillYearsInputFieldAndCheckCurrentPayment(max + 1);
    }

    public boolean fillYearsInputFieldAndCheckCurrentPayment(int years) {
        yearsInputField.click();
        yearsInputField.sendKeys(Keys.BACK_SPACE);
        yearsInputField.sendKeys(Keys.BACK_SPACE);
        yearsInputField.setValue(String.valueOf(years)).pressEnter();
        Selenide.sleep(1000);
        if (!(currentPayment.getText().equals("") || currentPayment.getText().equals(""))) {
            return false;
        }
        return true;
    }
}

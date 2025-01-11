package com.example.mts_testing.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 Класс страницы https://www.mtsbank.ru/chastnim-licam/ipoteka/
 */
public class MortgageCalculatorPage {
    /** "Кнопка Калькулятор*/
    private final SelenideElement calculatorButton = $x("//div[text()='Калькулятор']");

    /** Поле ввода для количества лет*/
    private final SelenideElement yearsInputField = $x("//div[@label='Срок кредита']/child::input");

    /** Сумма ежемесячного платежа в десктопной версии*/
    private final SelenideElement currentPayment = $x("//div[text()='Ежемес. платеж']/following-sibling::h4[@class='Wrapper-sc-6nwvzq-0 ckobOi']");

    /** Сумма ежемесячного платежа в мобильной версии*/
    private final SelenideElement currentPaymentMobile = $x("//div[text()='Платеж']/following-sibling::div[@class='Wrapper-sc-1vydk7-0 fWmnTq']");

    /** Минимальное количество лет на ипотеку*/
    private final SelenideElement minYears = $x("//div[text()='от 3 лет']");

    /** Максимальное количество лет на ипотеку*/
    private final SelenideElement maxYears = $x("//div[text()='до 30 лет']");

    /** Элемент с надписью "Первоначальный взнос" - нужен, чтоб до него скроллить страницу*/
    private final SelenideElement firstPayment = $x("//div[text()='Первоначальный взнос']");

    /** Конструктор, который определяет нужно ли открывать мобильную версию страницы, затем открывает браузер по ссылке из аргумента
     * @param mobileVersion true - нужна мобильная версия, false - десктопная
     * @param url ссылка, которую нужно открыть*/
    public MortgageCalculatorPage(String url, boolean mobileVersion){

        if (mobileVersion){
            System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        }

        Selenide.open(url);
        calculatorButton.click();
        firstPayment.scrollIntoView(true);
    }

    /**Метод запонения поля количества лет на ипотеку и сравнения платежей: текущего и предыдущего.*/
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

    /**Метод заполнение некорректных значений количества лет на ипотеку*/
    public boolean fillWrongData() {
        int min = Integer.parseInt(minYears.getText().replaceAll("[^0-9]", ""));
        int max = Integer.parseInt(maxYears.getText().replaceAll("[^0-9]", ""));
        return fillYearsInputFieldAndCheckCurrentPayment(min - 1) && fillYearsInputFieldAndCheckCurrentPayment(max + 1);
    }

    /**Метод заполнение некорректных значений количества лет
     * на ипотеку и проверка существования для них ежемесячного платежа*/
    public boolean fillYearsInputFieldAndCheckCurrentPayment(int years) {

        yearsInputField.click();
        yearsInputField.sendKeys(Keys.BACK_SPACE);
        yearsInputField.sendKeys(Keys.BACK_SPACE);
        yearsInputField.setValue(String.valueOf(years)).pressEnter();

        Selenide.sleep(1000);

        if ((currentPayment.isDisplayed() && !(currentPayment.getText().equals("") || currentPayment.getText().equals(""))) ||
                (currentPaymentMobile.isDisplayed() && !(currentPaymentMobile.getText().equals("") || currentPaymentMobile.getText().equals("")))) {
            return false;
        }
        return true;
    }
}

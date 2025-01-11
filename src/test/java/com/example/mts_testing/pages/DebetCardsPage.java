package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 Класс страницы https://www.mtsbank.ru/chastnim-licam/karti/all/debet/
 */

public class DebetCardsPage {

    /** Кнопка "Карты" на главной странице сайта https://www.mtsbank.ru */
    private final SelenideElement cardsButton = $x("//a[@href='/chastnim-licam/karti/' and @class='LinkWrapper-sc-f1cacf38-0 jeazkY sc-5055b9e7-5 fYIeVB']");
    /** Кнопка "Дебетовые" на странице https://www.mtsbank.ru/chastnim-licam/karti/ */
    private final SelenideElement debetCardsButton = $x("//a[@href='/chastnim-licam/karti/all/debet/' and @class='TabItem-sc-a22f654e-4 cSHyEg']");
    /** Текущий процент заполненности формы на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/ */
    private final SelenideElement currentFill = $x("//div[@class='Title-sc-290e3282-1 cgDJFY']");
    /** Ожидаемый процент заполненности формы на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/ */
    private final SelenideElement expectedFill = $x("//div[@class='Wrapper-sc-6189bc63-0 qLEoo']");
    /** Поле ввода номера телефона в форме на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/ */
    private final SelenideElement phoneNumberInput = $x("//input[@type='tel']");
    /** Поле ввода ФИО в форме на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/ */
    private final SelenideElement nameInput = $x("//textarea[@name='clientFio']");
    /** Поле ввода почты в форме на странице https://www.mtsbank.ru/chastnim-licam/karti/all/debet/ */
    private final SelenideElement emailInput = $x("//input[@type='email']");
   /** Конструктор, который определяет нужно ли открывать мобильную версию страницы, затем открывает браузер по ссылке из аргумента
    * @param mobileVersion true - нужна мобильная версия, false - десктопная
    * @param url ссылка, которую нужно открыть*/
    public DebetCardsPage(String url, boolean mobileVersion){
        if (mobileVersion){
            System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        }
        Selenide.open(url);
    }

    /** Метод заполнения полей формы и проверки добавления процентов её заполненности.
     * @param phoneNumber номер телефона
     * @param name ФИО
     * @param email почта*/
    public boolean fillDataAndCheckPercent(String phoneNumber, String name, String email){
        String expectedPercent = expectedFill.getText().replaceAll("[^0-9]", "");
        phoneNumberInput.setValue(phoneNumber).pressEnter();
        nameInput.setValue(name).pressEnter();
        emailInput.setValue(email).pressEnter();
        String currentPercent = currentFill.getText().replaceAll("[^0-9]", "");
        if(expectedPercent.equals(currentPercent)){
            return true;
        }
        return false;
    }

}

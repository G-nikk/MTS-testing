package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 Класс страницы https://www.mtsbank.ru
 */
public class MainPage {

    /** Кнопка "Узнать"*/
    private final SelenideElement findOutButton = $x("//div[text()='Узнать']");

    /** Поле ввода номера телефона*/
    private final SelenideElement phoneNumberInput = $x("//input[@type='tel']");

    /** Кнопка "Подтвердить"*/
    private final SelenideElement submitButton = $x("//div[text()='Подтвердить']");

    /** Кнопка закрытия мешающего баннера*/
    private final SelenideElement closeBannerButton = $x("//button[@class='styled__Close-sc-37ac7ba-1 jaMEbP']");

    /** Кнопка подтверждения региона*/
    private final SelenideElement submitRegionButton = $x("//div[text()='Да, верно']");

    /** Конструктор, который определяет нужно ли открывать мобильную версию страницы, затем открывает браузер по ссылке из аргумента
     * @param mobileVersion true - нужна мобильная версия, false - десктопная
     * @param url ссылка, которую нужно открыть*/
    public MainPage(String url, boolean mobileVersion){

        if (mobileVersion){
            System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        }

        Selenide.open(url);
        Selenide.sleep(1000);

        if (closeBannerButton.isDisplayed()){
            closeBannerButton.click();
        }

        if (submitRegionButton.isDisplayed()) {
            submitRegionButton.click();
        }
    }

    /**Метод ввода номера телефона в поле*/
    public void enterPhoneNumber(String phoneNumber){
        phoneNumberInput.scrollIntoView(true);
        phoneNumberInput.setValue(phoneNumber);
    }

    /**Метод нажатия на кнопку "Узнать"*/
    public void clickFindOutButton(){
        phoneNumberInput.scrollIntoView(true);
        findOutButton.click();
    }

    /**Этот метод проверяет, присутствует ли на экране кнопка "Подтвердить"
     * @return true, если кнопка есть на странице и false, если нет*/
    public boolean searchSubmitButton(){
        return submitButton.isDisplayed();
    }
}

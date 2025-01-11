package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement findOutButton = $x("//div[text()='Узнать']");
    private final SelenideElement phoneNumberInput = $x("//input[@type='tel']");
    private final SelenideElement submitButton = $x("//div[text()='Подтвердить']");

    public MainPage(String url){
        Selenide.open(url);
    }

    public void enterPhoneNumber(String phoneNumber){
        phoneNumberInput.scrollIntoView(false);
        phoneNumberInput.setValue(phoneNumber);
    }

    public void clickFindOutButton(){
        findOutButton.scrollIntoView(false);
        findOutButton.click();
    }

    public boolean searchSubmitButton(){
        return submitButton.isDisplayed();
    }
}

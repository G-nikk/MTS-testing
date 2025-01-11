package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement findOutButton = $x("//div[text()='Узнать']");
    private final SelenideElement phoneNumberInput = $x("//input[@type='tel']");
    private final SelenideElement submitButton = $x("//div[text()='Подтвердить']");
    private final SelenideElement closeBannerButton = $x("//button[@class='styled__Close-sc-37ac7ba-1 jaMEbP']");
    private final SelenideElement submitRegionButton = $x("//div[text()='Да, верно']");

    public MainPage(String url, boolean mobileVersion){
        if (mobileVersion){
            System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        }
        Selenide.open(url);
        Selenide.sleep(1000000000);
        if (closeBannerButton.isDisplayed()){
            closeBannerButton.click();
        }
        if (submitRegionButton.isDisplayed()) {
            submitRegionButton.click();
        }

    }

    public void enterPhoneNumber(String phoneNumber){
        phoneNumberInput.scrollIntoView(true);
        phoneNumberInput.setValue(phoneNumber);
    }

    public void clickFindOutButton(){
        phoneNumberInput.scrollIntoView(true);
        findOutButton.click();
    }

    public boolean searchSubmitButton(){
        return submitButton.isDisplayed();
    }
}

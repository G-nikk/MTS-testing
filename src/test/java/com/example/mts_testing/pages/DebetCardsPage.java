package com.example.mts_testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DebetCardsPage {
    private final SelenideElement cardsButton = $x("//a[@href='/chastnim-licam/karti/' and @class='LinkWrapper-sc-f1cacf38-0 jeazkY sc-5055b9e7-5 fYIeVB']");
    private final SelenideElement debetCardsButton = $x("//a[@href='/chastnim-licam/karti/all/debet/' and @class='TabItem-sc-a22f654e-4 cSHyEg']");
    private final SelenideElement currentFill = $x("//div[@class='Title-sc-290e3282-1 cgDJFY']");
    private final SelenideElement expectedFill = $x("//div[@class='Wrapper-sc-6189bc63-0 qLEoo']");
    private final SelenideElement phoneNumberInput = $x("//input[@type='tel']");
    private final SelenideElement nameInput = $x("//textarea[@name='clientFio']");
    private final SelenideElement emailInput = $x("//input[@type='email']");

    public DebetCardsPage(String url){
        Selenide.open(url);
        Selenide.sleep(1000);
        cardsButton.click();
        debetCardsButton.click();
    }

    public boolean fillData(String phoneNumber, String name, String email){
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

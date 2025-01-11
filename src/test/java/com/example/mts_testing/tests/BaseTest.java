package com.example.mts_testing.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class BaseTest {

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver131.exe");
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
        System.clearProperty("webdriver.chrome.driver");
    }
}

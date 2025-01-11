package com.example.mts_testing.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;

abstract class BaseTest {

    @Before
    public void  setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver131.exe");
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
        System.clearProperty("webdriver.chrome.driver");
    }
}

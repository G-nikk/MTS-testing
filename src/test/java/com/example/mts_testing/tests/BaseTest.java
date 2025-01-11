package com.example.mts_testing.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**Этот абстрактный класс предоставляет базовые методы для остальных тестов*/
abstract class BaseTest {

    /**Перед каждым из тестов этот метод указывает системе использовать драйвер Google Chrome 131*/
    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver131.exe");
    }

    /**После каждого теста этот метод указывает системе закрывать
     * драйвер Google Chrome 131 и очищать настройки конфигурации*/
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
        System.clearProperty("webdriver.chrome.driver");
    }
}

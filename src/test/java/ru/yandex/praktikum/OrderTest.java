package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

@RunWith(Parameterized.class)

public class OrderTest extends BaseTest{
    private WebDriver webDriver;
    private static final String BROWSE = "firefox";
    private String index;
    private String button;
    public OrderTest(String index, String button) {
        this.index = index;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {"Верхняя","Header"},
                {"Нижняя","Home"}
        };
    }

//Тест orderNotFound исключён, т.к. по сценарию нет проверки статуса заказа

    @Test
    public void createOrderOne(){

        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        webDriver = baseTest.setup(BROWSE);

        //Создаем объект класса главного окна и вызываем метод нажатия кнопки Заказать
        MainPage mainPage = new MainPage(webDriver);
        //mainPage.clickCreateOrder(index, button);
        mainPage.clickCreateOrder();
        //Закрываем окно с куки
        mainPage.closeCookiesWind();

        //Создаем объект класса оформления заказа
        OrderPage orderPage = new OrderPage(webDriver);
        //Вводим данные пользователя
        orderPage.fullCustomerInfo("Елена", "Тиханова", "Тольятти", "Арбатская", "89033326390");
        orderPage.clickNextBtn();

        //Вводим срок аренды
        orderPage.orderRent("01.01.2025", "сутки");
        orderPage.clickNextOrderButton();

        //Подтверждаем аренду
        orderPage.getAgreeOrder();

        //Проверяем окно об успешном создании заказа
        Assert.assertTrue(orderPage.issuedOrderText());

        //Закрываем браузер
        // По заданию: Метод driver.quit(); нужно использовать в конце каждого теста.
        webDriver.quit();
    }

    @Test
    public void createOrderTwo() {

        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        webDriver = baseTest.setup(BROWSE);

        //Создаем объект класса главного окна и вызываем метод нажатия кнопки Заказать
        MainPage mainPage = new MainPage(webDriver);
        //mainPage.clickCreateOrder(index, button);
        mainPage.clickCreateOrder();
        //Закрываем окно с куки
        mainPage.closeCookiesWind();

        //Создаем объект класса оформления заказа
        OrderPage orderPage = new OrderPage(webDriver);
        //Вводим данные пользователя
        orderPage.fullCustomerInfo("Иванов", "Павел", "Самара", "Лубянка", "89011155896");
        orderPage.clickNextBtn();

        //Вводим срок аренды
        orderPage.orderRent("25.05.2024", "трое суток");
        orderPage.clickNextOrderButton();

        //Подтверждаем аренду
        orderPage.getAgreeOrder();

        //Проверяем окно об успешном создании заказа
        Assert.assertTrue(orderPage.issuedOrderText());

        //Закрываем браузер
        // По заданию: Метод driver.quit(); нужно использовать в конце каждого теста.
        webDriver.quit();
    }
}
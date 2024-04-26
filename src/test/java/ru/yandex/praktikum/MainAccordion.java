package ru.yandex.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class MainAccordion {
    private static final String BROWSE = "firefox";
    private WebDriver webDriver;
    private int index;
    private String answer;

    public MainAccordion(int index, String answer) {
        this.index = index;
        this.answer = answer;
    }

    @Parameterized.Parameters
// массив ответов
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void mainQuestionTest() {
        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        webDriver = baseTest.setup(BROWSE);

        //Создаем объект класса
        MainPage mainPage = new MainPage(webDriver);

        //Кликаем на кнопку куки, чтобы она не загараживала обзор другим элементам
        mainPage.closeCookiesWind();

        //Кликаем на вопрос по индексу
        mainPage.expandQuestion(index);

        //Получаем ответ
        boolean answerIsDisplayed = mainPage.answerQuestion(answer);

        //Проверяем вопрос-ответ
        assertTrue(answerIsDisplayed);

        //Закрываем браузер
        // По заданию: Метод driver.quit(); нужно использовать в конце каждого теста.
        webDriver.quit();
    }
}
package ru.yandex.praktikum.page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver webDriver;

    //главная страница, кнопка Статуса заказа
    private By ordStateLocator = By.xpath("//button[text()='Статус заказа']");
    //страница поиска заказа, поле Введите номер заказа
    private By clickOrdStationBtnLoctor = By.id("cookiesBtnLocator");
    //страница поиска заказа, кнопка GO
    private By clickGoBtnLocator = By.xpath("//button[text()='Go!']");
    //страница поиска заказа, картинка Заказ не найден
    private By notFoundImgLocator = By.xpath("//img[@alt='Not found']");
    //главная страница, кнопка Заказать
    private By createOrderBtnLocator = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //главная страница, всплывающее окно про кукки, кнопка Да все привыкли
    private By cookiesBtnLocator = By.xpath("//button[text()='да все привыкли']");
    //главная страница, Аккордеон, вопрос
    private final String quationLocator = "accordion__heading-%s";
    //главная страница, Аккордеон, ответ
    private final String answerLocator = "//div[containts(@id='accordion__panel')][.=%s]";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Без клика куки, не видно кнопку Далее
    public void closeCookiesWind() {
        webDriver.findElement(cookiesBtnLocator).click();
    }

    // ПОИСК ЗАКАЗА
    // нажать кнопку Статуса заказа на главной странице
    public void clickOrderStatusBtn() {
        WebElement orderStatusBtn = webDriver.findElement(ordStateLocator);
        orderStatusBtn.click();
    }

    // заполнить поле Введите номер заказа
    public void enterOrderNumber(String orderNum) {
        WebElement orderInput = webDriver.findElement(clickOrdStationBtnLoctor);
        orderInput.sendKeys(orderNum);
    }

    // нажать кнопку GO
    public void clickGoBtn() {
        WebElement goBtn = webDriver.findElement(clickGoBtnLocator);
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.elementToBeClickable(goBtn));
        goBtn.click();
    }
    // отобразится картинка Заказ не найден
    public boolean notFoundImgIsDisplayed() {
        return webDriver.findElement(notFoundImgLocator).isDisplayed();
    }

    // нажать кнопку Заказать на главной странице
    public void clickCreateOrder(){
        WebElement orderStatusBtn = webDriver
                .findElement(createOrderBtnLocator);
        orderStatusBtn.click();
    }

    //АККОРДИОН

    // проверка вопросов в аккордионе
    public void expandQuestion(int index) {
        WebElement element =  webDriver.findElement(By.id(String.format(quationLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",element);
        //подождать пока элемент станет кликабельным
        //new WebDriverWait(webDriver, 15).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // проверка ответов в аккордионе
    public boolean answerQuestion(String expectedAnswer) {
        WebElement element =  webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
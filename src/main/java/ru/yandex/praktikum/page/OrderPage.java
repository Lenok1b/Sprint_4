package ru.yandex.praktikum.page;

import org.openqa.selenium.*;

public class OrderPage {
    private final WebDriver webDriver;
    //страница Для кого самокат, поле Имя
    private final By nameLocator = By.xpath("//input[@placeholder='* Имя']");
    //страница Для кого самокат, поле Фамилия
    private final By lastNameLocator = By.xpath("//input[@placeholder='* Фамилия']");
    //страница Для кого самокат, поле Адрес
    private final By addressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //страница Для кого самокат, поле Метро
    private final By stMetroLocator = By.xpath("//input[@placeholder='* Станция метро']");
    //страница Для кого самокат, поле Телефон
    private final By phoneLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //страница Для кого самокат, кнопка Далее
    private final By clickNextBtnLocator = By.xpath("//button[text()='Далее']");

    //страница Про аренду, поле Адрес куда вести самокат
    private final By whenOrder = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //страница Про аренду, поле для списка срока оренды
    private final By rentPeriodInput = By.xpath("//div[text()='* Срок аренды']");
    //страница Про аренду, кнопка Заказать (для оформления заказа), их две, находим по классу
    private final By clickOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка подтверждения заказа
    private final By clickAgreeOrderButton = By.xpath("//button[text()='Да']");

    //Проверка текста оформленного заказа       //Изменено
    private final By issuedOrder = By.xpath("//*[@class='Order_ModalHeader__3FDaJ']");


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // заполнение страницы Заказчика
    public void fullCustomerInfo(String name, String lastName, String adress, String subway, String phone) {
        // заполнение поля Имя
        WebElement nameInput = webDriver.findElement(nameLocator);
        nameInput.sendKeys(name);
        // заполнение поля Фамилия
        WebElement secondNInput = webDriver.findElement(lastNameLocator);
        secondNInput.sendKeys(lastName);
        // заполнение поля Адрес
        WebElement surnameInput = webDriver.findElement(addressLocator);
        surnameInput.sendKeys(adress);

        // заполнение поля Метро
        WebElement stMetroMenu = webDriver.findElement(stMetroLocator);
        stMetroMenu.sendKeys(subway, Keys.DOWN, Keys.ENTER);
        // прокрутка для поиска конкретной станции метро, не всегда срабатываат
//        WebElement arbatskayaStationMenu = webDriver.findElement(By.xpath("//button[@value='78'][./div[text()='" + stMetroLocator + "']]"));
//        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", arbatskayaStationMenu);
//        arbatskayaStationMenu.click();

        // заполнение поля Телефон
        WebElement phoneInput = webDriver.findElement(phoneLocator);
        phoneInput.sendKeys(phone);

    }
    // нажать кнопку Далее
    public void clickNextBtn() {
        WebElement nextBtn = webDriver
                .findElement(clickNextBtnLocator);
        nextBtn.click();
    }

    //заполнение формы Про аренду
    public void orderRent(String dateNumber, String filledDate) {
        webDriver.findElement(whenOrder).sendKeys(dateNumber, Keys.ENTER);
        webDriver.findElement(rentPeriodInput).click();
        webDriver.findElement(By.xpath("//div[text()='" + filledDate  + "']")).click();
    }
    // нажать на кнопку Далее
    public void clickNextOrderButton() {
        webDriver.findElement(clickOrderButton).click();
    }

    //нажать кнопку подтверждения заказа
    public void getAgreeOrder() {
        webDriver.findElement(clickAgreeOrderButton).click();
    }

    //проверка текста оформленного заказа
    public boolean issuedOrderText() {
        //Исправлено
        return webDriver.findElement(issuedOrder).isDisplayed();
    }
}
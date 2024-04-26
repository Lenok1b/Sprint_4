package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.page.WebDriverFactory;

import static ru.yandex.praktikum.page.Constanta.URL_CONST;

public class BaseTest {
    private WebDriver webDriver ;

    //Открыть браузер
    public WebDriver setup(String browser){
        ChromeOptions options = new ChromeOptions(); // Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", browser));
        webDriver.get(URL_CONST);
        return webDriver;
    }
}
package ru.yandex.praktikum.page;
//Если бразуер не запускает удалить этот input и добавить заново
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    //private WebDriverFactory webDriverFactory = new WebDriverFactory();
    public static WebDriver getWebDriver(String browserType){
        //запуск браузера
        if (browserType.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else // (browserType.equalsIgnoreCase("Chrome"))
        {
            WebDriverManager.chromedriver().setup();
            return  new ChromeDriver();
        }
    }
}
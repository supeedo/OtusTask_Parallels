package ru.habr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.habr.pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;

public class TestClass2 {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("84.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        driver = new RemoteWebDriver(
                new URL("http://0.0.0.0:4444/wd/hub"),
                capabilities
        );

        mainPage = new MainPage(driver);
        driver.navigate().to("https://habr.com/ru/");
    }

    @Test
    public void test() {
        mainPage
                .clickElHubsLink()
                .checkURL();
    }

    @AfterMethod
    public void close() {
        if(driver != null) {
            driver.close();
        }
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BaseTest{
    protected WebDriver driver;

    @Before
    public void setupBrowser() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/java/resources/.properties"));
        driver = getDriver(properties.getProperty("Browser"));
        driver.get(MainPage.URL);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver (String driverType)
    {
        switch (driverType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "mozilla":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Driver type is not supported");
        }
    }
}


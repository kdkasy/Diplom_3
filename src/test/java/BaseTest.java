import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest{
    protected WebDriver driver;

    @Before
    public void setupBrowser(){
        driver = getDriver("chrome");
        driver.get(MainPage.URL);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver (String driverType)
    {
        switch (driverType) {
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


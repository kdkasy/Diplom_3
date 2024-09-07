package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage{

    @FindBy
    private final By profileTitle = By.xpath(".//a[text() = 'Профиль']");

    @FindBy
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    @FindBy
    private final By constructorButton = By.xpath(".//a[@class = 'AppHeader_header__link__3D_hX']");

    @FindBy
    private final By stellarLogoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']/a");

    @FindBy
    private final By downloadPageModal = By.className("Modal_modal_overlay__x2ZCr");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check is profile page downloaded")
    public boolean isProfilePageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileTitle));
        return driver.findElement(profileTitle).isDisplayed();
    }

    @Step("Click on constructor from profile page")
    public MainPage clickOnConstructor() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
        return new MainPage(driver);
    }

    @Step("Click on Stellar burger logo from profile page")
    public MainPage clickOnStellarLogo() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.elementToBeClickable(stellarLogoButton));
        driver.findElement(stellarLogoButton).click();
        return new MainPage(driver);
    }

    @Step("Click on exit from profile page")
    public LogInPage clickOnExitButton() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
        return new LogInPage(driver);
    }


}

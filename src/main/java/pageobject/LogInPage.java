package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BasePage{

    @FindBy
    private final By registerButton = By.xpath(".//a[text() = 'Зарегистрироваться']");

    @FindBy
    private final By emailField = By.xpath(".//label[text() = 'Email']/following-sibling::input");

    @FindBy
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/following-sibling::input");

    @FindBy
    private final By enterButton = By.xpath(".//button[text() = 'Войти']");
    @FindBy
    private final By enterLogo = By.xpath("//h2[text() = 'Вход']");

    @FindBy
    private final By restorePasswordButton = By.xpath(".//a[text() = 'Восстановить пароль']");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on registration button from login page")
    public RegisterPage clickOnRegisterButton () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }

    @Step("Check log in page downloaded")
    public boolean checkLogInPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterLogo));
        return driver.findElement(enterLogo).isDisplayed();
    }

    @Step("Log in from login page")
    public MainPage logIn(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterLogo));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(enterButton).click();
        return new MainPage(driver);
    }

    @Step("Click on restore password from login page")
    public RestorePasswordPage clickOnRestorePassword() {
        driver.findElement(restorePasswordButton).click();
        return new RestorePasswordPage(driver);

    }
}

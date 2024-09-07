package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage{

    @FindBy
    private final By registerButton =  By.xpath(".//button[text() = 'Зарегистрироваться']");

    @FindBy
    private final By nameField = By.xpath(".//label[text() = 'Имя']/following-sibling::input");

    @FindBy
    private final By emailField = By.xpath(".//label[text() = 'Email']/following-sibling::input");

    @FindBy
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/following-sibling::input");

    @FindBy
    private final By unCorrectPasswordError = By.xpath(".//p[text() = 'Некорректный пароль']");

    @FindBy
    private final By registerLogo = By.xpath(".//h2[text() = 'Регистрация']");

    @FindBy
    private final By enterButton = By.xpath(".//a[text() = 'Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill registration fields and click registration button")
    public void fillFieldsAndRegister (String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerLogo));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Check uncorrected password error appeared")
    public boolean isPasswordErrorAppeared() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(registerLogo));
        return driver.findElement(unCorrectPasswordError).isDisplayed();
    }

    @Step("Click on enter button from registration page")
    public LogInPage clickOnEnterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        driver.findElement(enterButton).click();
        return new LogInPage(driver);
    }
}

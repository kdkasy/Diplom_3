package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RestorePasswordPage extends BasePage{

    @FindBy
    private final By restorePasswordLogo = By.xpath(".//h2[text() = 'Восстановление пароля']");

    @FindBy
    private final By enterButton = By.xpath(".//a[text() = 'Войти']");

    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on enter button from restore password page ")
    public LogInPage clickOnEnterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(restorePasswordLogo));
        driver.findElement(enterButton).click();
        return new LogInPage(driver);
    }
}

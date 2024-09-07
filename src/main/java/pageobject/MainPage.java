package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage{
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy
    private final By logInButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    @FindBy
    private final By personalAccountButton = By.xpath("//p[text() = 'Личный Кабинет']");

    @FindBy
    private final By createBurgerlogo = By.xpath(".//h1[text() = 'Соберите бургер']");

    @FindBy
    private final By bunSection = By.xpath(".//span[text() = 'Булки']");

    @FindBy
    private final By sauceSection = By.xpath(".//span[text() = 'Соусы']");

    @FindBy
    private final By fillingSection = By.xpath(".//span[text() = 'Начинки']");

    @FindBy
    private final By ingredientsSection = By.xpath(".//span[text() = 'Начинки']/parent::div/parent::div");

    @FindBy
    private final By downloadPageModal = By.className("Modal_modal_overlay__x2ZCr");

    private final String SECTION_SELECTED_CLASS = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private final String SECTION_NOT_SELECTED_CLASS = "tab_tab__1SPyG pt-4 pr-10 pb-4 pl-10 noselect";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on log in button from main page")
    public LogInPage clickOnLogInButton() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInButton));
        driver.findElement(logInButton).click();
        return new LogInPage(driver);
    }

    @Step("Click on personal account button from main page")
    public LogInPage clickOnPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new LogInPage(driver);
    }

    @Step("Check is main page downloaded")
    public boolean isMainPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createBurgerlogo));
        return driver.findElement(createBurgerlogo).isDisplayed();
    }

    @Step("Click on personal account button from main page with authorization")
    public ProfilePage clickOnPersonalAccountWhenLoggedIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    @Step("Click on sauce section and check others are not selected")
    public boolean openSauceSectionAndCheckItSelected() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
        driver.findElement(sauceSection).click();
        return checkSelectedSection(driver.findElement(sauceSection).getText());
    }

    @Step("Click on bun section and check others are not selected")
    public boolean openBunSectionAndCheckItSelected() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunSection));
        driver.findElement(bunSection).click();
        return checkSelectedSection(driver.findElement(bunSection).getText());
    }

    @Step("Click on filling section and check others are not selected")
    public boolean openFillingSectionAndCheckItSelected() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(downloadPageModal)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
        driver.findElement(fillingSection).click();
        return checkSelectedSection(driver.findElement(fillingSection).getText());
    }

    public boolean checkSelectedSection(String nameOfSection){
        List<WebElement> divsOfSection = driver.findElement(ingredientsSection).findElements(By.xpath("/div"));
        List<WebElement> spansOfSection = driver.findElement(ingredientsSection).findElements(By.xpath("/span"));
        for(int i = 0; i < divsOfSection.size(); i++){
            if(nameOfSection.equals(spansOfSection.get(i).getText())){
                if(SECTION_SELECTED_CLASS.equals(divsOfSection.get(i).getAttribute("class"))) { }
                else {
                    return false;
                }
            }
            else {
                if(SECTION_NOT_SELECTED_CLASS.equals(divsOfSection.get(i).getAttribute("class"))) { }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}


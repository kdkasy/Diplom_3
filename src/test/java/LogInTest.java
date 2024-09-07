import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pageobject.MainPage;

import static org.junit.Assert.*;

public class LogInTest extends BaseTest {
    private UserClient userClient;
    private User user;
    private String accessToken;


    @Before
    public void createUser() {
        userClient = new UserClient();
        user = UserGenerator.getUser();

        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
    }

    @Test
    @Description("Successful log in from log in on main page")
    public void logInFromLogInOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnLogInButton().logIn(user.getEmail(), user.getPassword()).isMainPageOpened());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
        assertNotEquals(null, accessToken);
    }

    @Test
    @Description("Successful log in from personal account on main page")
    public void logInFromPersonalAccountOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnPersonalAccountButton().logIn(user.getEmail(), user.getPassword()).isMainPageOpened());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
        assertNotEquals(null, accessToken);
    }

    @Test
    @Description("Successful log in from registration page")
    public void logInFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnPersonalAccountButton().clickOnRegisterButton().clickOnEnterButton().logIn(user.getEmail(), user.getPassword()).isMainPageOpened());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
        assertNotEquals(null, accessToken);
    }

    @Test
    @Description("Successful log in from restore password page")
    public void logInFromRestorePasswordPage() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnLogInButton().clickOnRestorePassword().clickOnEnterButton().logIn(user.getEmail(), user.getPassword()).isMainPageOpened());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
        assertNotEquals(null, accessToken);
    }

    @After
    public void deleteUser() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("localStorage.clear();");
        userClient.deleteUser(accessToken);
    }
}

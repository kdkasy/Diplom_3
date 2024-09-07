import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pageobject.MainPage;

import static org.junit.Assert.*;

public class ExitTest extends BaseTest{

    private UserClient userClient;
    private String accessToken;

    @Before
    public void createUser() {
        userClient = new UserClient();
        User user = UserGenerator.getUser();

        userClient.createUser(user);

        ValidatableResponse logInResponse = userClient.logIn(user);
        accessToken = logInResponse.extract().path("accessToken");
        String refreshToken = logInResponse.extract().path("refreshToken");


        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(String.format("localStorage.setItem('%s', '%s')", "accessToken", accessToken));
        js.executeScript(String.format("localStorage.setItem('%s', '%s')", "refreshToken", refreshToken));
    }

    @Test
    @Description("Exit from account test")
    public void exitFromAccountTest() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnPersonalAccountWhenLoggedIn().clickOnExitButton().checkLogInPageOpened());
        assertEquals(0L, ((JavascriptExecutor)driver).executeScript(("return localStorage.length;")));
    }


    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }
}

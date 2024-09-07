import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pageobject.MainPage;

import static org.junit.Assert.*;

public class PerconalAccountTest extends BaseTest{

    @Test
    @Description("Verify that opens profile page by click on personal account on main page when logged in")
    public void clickOnPersonalAccountWhenAuthorized() {
        UserClient userClient = new UserClient();
        User user = UserGenerator.getUser();

        userClient.createUser(user);

        ValidatableResponse logInResponse = userClient.logIn(user);
        String accessToken = logInResponse.extract().path("accessToken");
        String refreshToken = logInResponse.extract().path("refreshToken");


        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(String.format("localStorage.setItem('%s', '%s')", "accessToken", accessToken));
        js.executeScript(String.format("localStorage.setItem('%s', '%s')", "refreshToken", refreshToken));


        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnPersonalAccountWhenLoggedIn().isProfilePageOpened());


        js.executeScript("localStorage.clear();");
        userClient.deleteUser(accessToken);
    }

    @Test
    @Description("Verify that opens log in page by click on personal account on main page without authorization")
    public void clickOnPersonalAccountWhenNotAuthorized() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickOnLogInButton().checkLogInPageOpened());
    }
}

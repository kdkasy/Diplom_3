import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import pageobject.LogInPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    private User user;

    @Before
    public void createUser() {
        user = UserGenerator.getUser();
    }

    @Test
    @Description("Successful registration test")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLogInButton()
                .clickOnRegisterButton()
                .fillFieldsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        LogInPage logInPage = new LogInPage(driver);
        assertTrue( logInPage.checkLogInPageOpened());

        UserClient userClient = new UserClient();

        ValidatableResponse logInResponse = userClient.logIn(user);
        assertTrue(logInResponse.extract().path("success"));

        userClient.deleteUser(logInResponse.extract().path("accessToken"));
    }

    @Test
    @Description("Registration with short password")
    public void unCorrectPasswordTest() {
        user.setPassword(user.getPassword().substring(0, 3));
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLogInButton()
                .clickOnRegisterButton()
                .fillFieldsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue(registerPage.isPasswordErrorAppeared());
    }
}

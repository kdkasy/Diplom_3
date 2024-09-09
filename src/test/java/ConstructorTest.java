import io.qameta.allure.Description;
import org.junit.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest{
    @Test
    @Description("Test verifies switching between ingredients sections on main page")
    public void openSauceSectionInConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.isMainPageOpened();
        assertTrue(mainPage.openSauceSectionAndCheckItSelected());
    }

    @Test
    @Description("Test verifies switching between ingredients sections on main page")
    public void openFillingSectionInConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.isMainPageOpened();
        assertTrue(mainPage.openFillingSectionAndCheckItSelected());
    }

    @Test
    @Description("Test verifies switching between ingredients sections on main page")
    public void openBunSectionInConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.isMainPageOpened();
        mainPage.openFillingSectionAndCheckItSelected();
        assertTrue(mainPage.openBunSectionAndCheckItSelected());
    }
}

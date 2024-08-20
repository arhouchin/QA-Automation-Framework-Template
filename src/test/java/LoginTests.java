import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void navigateToKoelApp() {
        //Expected Results
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginValidEmailPassword() {
        try {
            Thread.sleep(2000);
            //Email Field
            provideEmail("aaron.houchin@testpro.io");
            Thread.sleep(2000);
            //Password Field
            providePassword("Arho!030");
            Thread.sleep(2000);
            //Submit Button
            clickSubmit();
            //Expected Results
            WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
            Assert.assertTrue(avatarIcon.isDisplayed());
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }

    }

    @Test
    public void loginWithInvalidEmailAndValidPassword() throws InterruptedException {
        //Email Field
        provideEmail("invalid@class.com");
        //Password Field
        providePassword("Arho!030");
        //Submit Button
        clickSubmit();
        Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginWithInvalidPasswordAndValidEmail() throws InterruptedException {
        //Email Field
        provideEmail("aaron.houchin@testpro.io");
        //Password Field
        providePassword("invalidpassword");
        //Submit Button
        clickSubmit();
        Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }



}

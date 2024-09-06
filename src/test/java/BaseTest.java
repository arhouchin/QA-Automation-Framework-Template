import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {

    //Data Providers
    @DataProvider(name="InvalidLoginData")
    public static Object[][] getDataFromDataProviders(){
        return new Object[][]{
                {"invalid@mail.com", "invalidPassword"},
                {"aaron.houchin@testpro.io", ""},
                {"", "Arho!030"},
                {"", ""}
        };
    }

    public WebDriver driver;

    public WebDriverWait wait;

    public String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() { WebDriverManager.chromedriver().setup(); }

    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl){
        //Chrome Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Manage Browser - Wait for 10 seconds before failing/quitting
        driver = new ChromeDriver(options);
        //Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        navigateToUrl(BaseUrl);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    void clickSubmit(){
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton.click();
    }

    //Helper Method
    public void navigateToUrl(String givenUrl){
        driver.get(givenUrl);
    }


}
package sauceDemo;
import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    private ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rehan Ali\\Documents\\automation\\chromedriver_win32.zip\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }

    @Test
    public void loginTest() {
        // Step 1: Manual Login
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Step 2: Automated Login and Validation
        Assert.assertTrue(driver.findElement(By.className("product_label")).isDisplayed(), "Login failed");

        // Step 3: Input Validation
        // Example: Validate the error message for an invalid password
        driver.findElement(By.id("password")).sendKeys("invalid_password");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed(), "Error message not displayed");

        // Step 4: Include Login and Logout in all tests
        // Example: Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed(), "Logout failed");
    }
}

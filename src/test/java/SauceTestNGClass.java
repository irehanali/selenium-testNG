import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ExtentTestNGITestListener.class)
public class SauceTestNGClass {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    @BeforeSuite
    public void setUpSuite(ITestContext context) {
        // Set up ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("path/to/report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        context.setAttribute("extent", extentReports);
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        // Update the ExtentReports with test results
        if (result.getStatus() == ITestResult.FAILURE)
            extentTest.fail("Test Failed");
        else if (result.getStatus() == ITestResult.SKIP)
            extentTest.skip("Test Skipped");
        else
            extentTest.pass("Test Passed");
    }

    @AfterSuite
    public void tearDownSuite() {
        // Flush and close the ExtentReports
        extentReports.flush();
        extentReports.close();
    }

    @Test
    public void loginTest() {
        // ... test code ...
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

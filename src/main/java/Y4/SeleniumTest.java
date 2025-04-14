package Y4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

    WebDriver driver;
    public UIMapNew uimap;
    public UIMapNew datafile;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
        uimap = new UIMapNew("src/main/resources/locator.properties");
        datafile = new UIMapNew("src/main/resources/data.properties");
    }

    @Test(description = "Login to Sauce Demo")
    public void loginUser() throws Exception {
        driver.get(uimap.getData("login_url"));
        WebElement usernameField = driver.findElement(uimap.getLocator("login_username_id"));
        usernameField.sendKeys(datafile.getData("login_username"));
        WebElement passwordField = driver.findElement(uimap.getLocator("login_password_id"));
        passwordField.sendKeys(datafile.getData("login_password"));
        WebElement loginButton = driver.findElement(uimap.getLocator("login_button_id"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(uimap.getLocator("welcome_message_id")));
        Assert.assertTrue(welcomeMessage.isDisplayed());
    }

    @Test(description = "Add - Add a product to cart (symbolic)", dependsOnMethods = "loginUser")
    public void addProduct() throws Exception {
        System.out.println("Navigating to: " + uimap.getData("products_url"));
        driver.get(uimap.getData("products_url"));
        System.out.println("Current URL: " + driver.getCurrentUrl());

        try {
            WebElement addLink = driver.findElement(uimap.getLocator("add_product_link_id"));
            System.out.println("Add button found: " + addLink.isDisplayed());
            addLink.click();
        } catch (Exception e) {
            System.out.println("Error finding add button: " + e.getMessage());
            throw e;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
            System.out.println("Cart badge visible: " + cartBadge.isDisplayed());
            System.out.println("Cart badge text: " + cartBadge.getText());
            Assert.assertTrue(cartBadge.isDisplayed());
            Assert.assertEquals(cartBadge.getText(), "1", "Expected one item in cart");
        } catch (Exception e) {
            System.out.println("Error finding cart badge: " + e.getMessage());
            throw e;
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
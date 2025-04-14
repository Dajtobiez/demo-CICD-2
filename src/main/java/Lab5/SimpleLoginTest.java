package Lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SimpleLoginTest {
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        try {
            String url = "https://www.saucedemo.com/";
            driver.get(url);

            driver.findElement(By.id("user-name")).sendKeys("standard_user");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            String actualUrl = driver.getCurrentUrl();

            if (actualUrl.contentEquals(expectedUrl)) {
                System.out.println("Test Pass - Login thành công!");
            } else {
                System.out.println("Test Fail - Login thất bại!");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
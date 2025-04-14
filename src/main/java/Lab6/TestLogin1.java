package Lab6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin1 {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}

	@Test
	public void testLogin() throws InterruptedException {
		Thread.sleep(4000);
		WebElement usename = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		Thread.sleep(4000);
		usename.sendKeys("student");
		password.sendKeys("Password123");
		Thread.sleep(4000);
		driver.findElement(By.id("submit")).click();
		Thread.sleep(4000);
		String result = driver.findElement(By.className("post-title")).getText();
		String expected = "Logged In Successfully";

		Assert.assertEquals(result, expected);
	}
}

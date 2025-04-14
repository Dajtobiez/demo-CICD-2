package Lab6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin2 {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new EdgeDriver();
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
		String result = driver.findElement(By.cssSelector("h1[class='post-title']")).getText();
		String expected = "Logged In Successfully";
		
		Assert.assertEquals(result, expected);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

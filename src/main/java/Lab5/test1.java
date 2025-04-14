package Lab5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class test1 {
    @Test
    public void test1() {
        WebDriver driver = new ChromeDriver();
        try {
            String url = "http://google.com";
            String title_web = "Google";
            driver.get(url);
            String title = driver.getTitle();
            if (title.contentEquals(title_web)) {
                System.out.println("Test Pass");
            } else {
                System.out.println("Test Fail");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
package Lab6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SaveTestNGResultToExcel {

    WebDriver driver;
    public UIMapNew uimap;
    public UIMapNew datafile;
    public String workingDir;

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    Map<String, Object[]> TestNGResults;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("TestNG Results");
        TestNGResults = new HashMap<>();

        uimap = new UIMapNew("src/main/resources/locator.properties");
        datafile = new UIMapNew("src/main/resources/data.properties");
    }
    @Test(description = "Opens the TestNG Demo Website for Login Test")
    public void launchWebsite() {
        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");

            TestNGResults.put("2", new Object[]{
                1d, "Navigate to demo website", "Site gets opened successfully", "Pass"
            });
        } catch (Exception e) {
            TestNGResults.put("2", new Object[]{
                1d, "Navigate to demo website", "Site failed to open", "Fail"
            });
            e.printStackTrace();
        }
    }
    @Test(description = "Fill the Login Details")
    public void fillLoginDetails() throws Exception {
        try {
            WebElement username = driver.findElement(uimap.getLocator("username"));
            username.sendKeys(datafile.getData("student"));

            WebElement password = driver.findElement(uimap.getLocator("password"));
            password.sendKeys(datafile.getData("Password123"));

            TestNGResults.put("3", new Object[]{
                2d, "Fill login form data (Username/Password)", "Login details are filled successfully", "Pass"
            });
        } catch (Exception e) {
            TestNGResults.put("3", new Object[]{
                2d, "Fill login form data (Username/Password)", "Login form filling failed", "Fail"
            });
        }
    }

    @Test(description = "Perform Login")
    public void doLogin() throws Exception {
        try {
            WebElement loginButton = driver.findElement(uimap.getLocator("submit"));
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(uimap.getLocator("post-title")));
            Assert.assertEquals(username.getText(), "Logged In Successfully");

            TestNGResults.put("4", new Object[]{
                3d, "Click Login and verify welcome message", "Login successful", "Pass"
            });
        } catch (Exception e) {
            TestNGResults.put("4", new Object[]{
                3d, "Click Login and verify welcome message", "Login failed", "Fail"
            });
        }
    }

    @AfterClass
    public void suiteTearDown() {
        Set<String> keyset = TestNGResults.keySet();
        int rownum = 0;
        for (String key : keyset) {
            HSSFRow row = sheet.createRow(rownum++);
            Object[] objArr = TestNGResults.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                HSSFCell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try (FileOutputStream out = new FileOutputStream(new File("SaveTestNGResultToExcel.xls"))) {
            workbook.write(out);
            System.out.println("Successfully saved Selenium WebDriver TestNG result to Excel File!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
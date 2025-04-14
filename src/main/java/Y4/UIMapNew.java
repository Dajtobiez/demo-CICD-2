package Y4;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class UIMapNew {
    Properties properties;

    public UIMapNew(String FilePath) {
        try {
            FileInputStream Locator = new FileInputStream(FilePath);
            properties = new Properties();
            properties.load(Locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getData(String ElementName) throws Exception {
        return properties.getProperty(ElementName);
    }

    public By getLocator(String ElementName) throws Exception {
        String locator = properties.getProperty(ElementName);
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
        if (locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if (locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.tagName(locatorValue);
        else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if (locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if (locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Locator type '" + locatorType + "' not defined!");
    }
}
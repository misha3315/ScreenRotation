package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class ScreenRotation {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/mike.zorin/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {

        driver.quit();
    }


    @Test
    public void testChangeScreenOrientationSearchResults() {

        waitForElementByXpathAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);

        String searchLine = "Java";

        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find search input",
                5);

        waitForElementByXpathAndClick(
                By.xpath("//android.widget.LinearLayout//*[@text='Island of Indonesia, Southeast Asia']"),
                "Cannot find 'Island of Indonesia, Southeast Asia' topic searching by " + searchLine + "",
                15);

        String titleBeforeRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        Assert.assertEquals(
                "Article does not match",
                titleBeforeRotation,
                titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

    }

    @Test
    public void testChangeScreenOrientationSearchResults2() {

        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.rotate(ScreenOrientation.PORTRAIT);

        waitForElementByXpathAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);

        String searchLine = "Java";

        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find search input",
                5);

        waitForElementByXpathAndClick(
                By.xpath("//android.widget.LinearLayout//*[@text='Java (programming language)']"),
                "Cannot find 'Java (programming language)' topic searching by " + searchLine + "",
                15);


        String titleBeforeRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        Assert.assertEquals(
                "Article does not match",
                titleBeforeRotation,
                titleAfterRotation);
    }

    @Test
    public void testChangeScreenOrientationSearchResults3() {

        driver.rotate(ScreenOrientation.PORTRAIT);

        waitForElementByXpathAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);

        String searchLine = "Java";

        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find search input",
                5);

        waitForElementByXpathAndClick(
                By.xpath("//android.widget.LinearLayout//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' topic searching by " + searchLine + "",
                15);

        String titleBeforeRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        Assert.assertEquals(
                "Article does not match",
                titleBeforeRotation,
                titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    private WebElement assertElementHasText(By by, String errorMessage, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementByXpathAndClick(By by, String errorMessage, long timeoutInSeconds) {

        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.click();
        return element;

    }

    private WebElement waitForElementByIdAndSandKeys(By by, String value, String errorMessage, long timeoutInSeconds) {

        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    private String waitForElementGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds) {

        WebElement element = assertElementHasText(by, errorMessage, 15);
        return attribute;
    }
}


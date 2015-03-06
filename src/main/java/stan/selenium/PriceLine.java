package stan.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

//import org.openqa.selenium.WebDriverBackedSelenium;


public class PriceLine {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.priceline.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPriceLine() throws Exception {

  driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");

    driver.get(baseUrl + "/");
    driver.findElement(By.id("tab-flights")).click();
    driver.findElement(By.cssSelector("#AirTripTypeLabelMD > span.ui-button-text")).click();
    driver.findElement(By.id("AirTripTypeMD")).click();
    driver.findElement(By.id("air-orig-md-0")).clear();
//    driver.findElement(By.id("air-orig-md-0")).sendKeys("Philadelphia, PA - Philadelphia Intl Airport (PHL)");
    driver.findElement(By.id("air-orig-md-0")).sendKeys("PHL");
    driver.findElement(By.id("air-dest-md-0")).clear();
    //driver.findElement(By.id("air-dest-md-0")).sendKeys("Varna, Bulgaria - Varna Aksakovo Airport (VAR)");
    driver.findElement(By.id("air-dest-md-0")).sendKeys("VAR");
    driver.findElement(By.id("air-date-md-0")).clear();
    driver.findElement(By.id("air-date-md-0")).sendKeys("07/10/2015");
    driver.findElement(By.id("multi-dest-add-link-0")).click();
    driver.findElement(By.id("air-orig-md-1")).clear();
    //driver.findElement(By.id("air-orig-md-1")).sendKeys("Madrid, Spain - Barajas Airport (MAD)");
    driver.findElement(By.id("air-orig-md-1")).sendKeys("MAD");
    driver.findElement(By.id("air-dest-md-1")).clear();
    //driver.findElement(By.id("air-dest-md-1")).sendKeys("Philadelphia, PA - Philadelphia Intl Airport (PHL)");
    driver.findElement(By.id("air-dest-md-1")).sendKeys("PHL");
    driver.findElement(By.id("air-date-md-1")).clear();
    driver.findElement(By.id("air-date-md-1")).sendKeys("08/8/2015");
    driver.findElement(By.id("air-btn-submit-retl")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | pu_airsearch | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=pu_airsearch | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp |  | 30000]]
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

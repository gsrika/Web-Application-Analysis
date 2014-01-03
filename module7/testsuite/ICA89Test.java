package selenium.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ICA89Test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testICA85() throws Exception {
    driver.get(baseUrl + "/bookstore/Registration.jsp");
    driver.findElement(By.name("member_login")).clear();
    driver.findElement(By.name("member_login")).sendKeys("newuser");
    driver.findElement(By.name("member_password")).clear();
    driver.findElement(By.name("member_password")).sendKeys("newuser");
    driver.findElement(By.name("member_password2")).clear();
    driver.findElement(By.name("member_password2")).sendKeys("newsuer");
    driver.findElement(By.name("first_name")).clear();
    driver.findElement(By.name("first_name")).sendKeys("srikanth");
    driver.findElement(By.name("last_name")).clear();
    driver.findElement(By.name("last_name")).sendKeys("gandupalli");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("srikanga");
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("angan");
    driver.findElement(By.name("phone")).clear();
    driver.findElement(By.name("phone")).sendKeys("21344784");
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

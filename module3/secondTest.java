package selenium.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class secondTest {
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
  public void testSecond() throws Exception {
    driver.get(baseUrl + "/bookstore/Login.jsp");
    driver.findElement(By.name("Login")).clear();
    driver.findElement(By.name("Login")).sendKeys("newuser1");
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("newuser1");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.xpath("//td[6]/a/font/img")).click();
    driver.findElement(By.xpath("//td[6]/a/font/img")).click();
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    driver.findElement(By.xpath("//td[2]/a/font/img")).click();
    driver.findElement(By.xpath("//tr[3]/td/a/font")).click();
    driver.findElement(By.xpath("//td[6]/a/font/img")).click();
    driver.findElement(By.xpath("//td[2]/a/font/img")).click();
    driver.findElement(By.xpath("//td[3]/a/font/img")).click();
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    driver.findElement(By.cssSelector("img")).click();
    driver.findElement(By.xpath("//tr[12]/td/a/font")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
    	String book1=driver.findElement(By.xpath("//tr[3]/td[3]/font")).getText();
        book1=book1.trim();
      assertEquals("MySQL & PHP From Scratch",book1 );
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("img")).click();
    driver.findElement(By.xpath("//tr[30]/td/a/font")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
    	String book2=driver.findElement(By.xpath("//tr[4]/td[3]/font")).getText();
        book2=book2.trim();
      assertEquals("Beginning ASP Databases", book2);
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//td[4]/a/font/img")).click();
    
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

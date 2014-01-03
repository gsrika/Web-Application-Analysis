package selenium.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class thirdTest {
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
  public void testThird() throws Exception {
    driver.get(baseUrl + "/bookstore/Login.jsp");
    driver.findElement(By.name("Login")).clear();
    driver.findElement(By.name("Login")).sendKeys("newuser1");
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("newuser1");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    String book1="";
    try {
    	book1=driver.findElement(By.xpath("//tr[3]/td[3]/font")).getText();
        book1=book1.trim();
      assertEquals("MySQL & PHP From Scratch", book1);
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//tr[3]/td/a/font")).click();
    driver.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete record[\\s\\S]$"));
    String book2=driver.findElement(By.xpath("//tr[3]/td[3]/font")).getText();
    book2.trim();
    assertTrue("book is deleted so book is not found", book1 != book2 ) ;
    
    
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

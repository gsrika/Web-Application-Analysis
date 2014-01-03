package selenium.selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ICA92Test {
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
  public void testFifth() throws Exception {
    driver.get(baseUrl + "/bookstore/Login.jsp");
    driver.findElement(By.name("Login")).clear();
    driver.findElement(By.name("Login")).sendKeys("guest");
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("guest");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.xpath("//td[6]/a/font/img")).click();
    try {

        String logoutText=driver.findElement(By.cssSelector("input[type=\"submit\"]")).getAttribute("value");
        logoutText=logoutText.trim();
      assertEquals("Logout", logoutText);
      
      File file=new File("/home/srikanth/gdrive/WorkSpace/ICA9/TestStatus.txt");
      if(!file.exists()){
  		file.createNewFile();
  	}
      FileWriter fileWritter = new FileWriter(file,true);
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      
           
      
      
      
      if("Logout".equals(logoutText))
      {
      	String s="ICA92Test:PASSED";
      	bufferWritter.write(s);
      	bufferWritter.write("\n");
      	
      }
      else 
      {
      	String s="ICA92Test:FAILED";
      	bufferWritter.write(s);
      	bufferWritter.write("/n");
      }
      bufferWritter.close();
      
      
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
    
    //second 
    
    
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
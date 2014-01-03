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

public class ICA99Test {
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
  public void testFirstLogin() throws Exception {
    driver.get(baseUrl + "/bookstore/MyInfo.jsp?member_password=guest&name=abc&last_name=&email=guest%40localhost&address=magnolia+2676&phone=2134478424&notes=fgaklghalkg&card_type_id=&card_number=gagjalg&FormName=Form&FormAction=update&member_id=2&PK_member_id=2");
    driver.findElement(By.name("Login")).clear();
    driver.findElement(By.name("Login")).sendKeys("guest");
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("guest");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.get(baseUrl + "/bookstore/MyInfo.jsp?member_password=guest&name=abc&last_name=&email=guest%40localhost&address=magnolia+2676&phone=2134478424&notes=fgaklghalkg&card_type_id=&card_number=gagjalg&FormName=Form&FormAction=update&member_id=2&PK_member_id=2");
    
    String register=driver.findElement(By.xpath("//tr[2]/td/font")).getText();
    register=register.trim();
    assertEquals("The value in field Last Name* is required.",register );
    File file=new File("/home/srikanth/gdrive/WorkSpace/ICA9/TestStatus.txt");
    if(!file.exists()){
		file.createNewFile();
	}
    FileWriter fileWritter = new FileWriter(file,true);
    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
    
    if("The value in field Last Name* is required.".equals(register))
    {
    	String s="ICA99Test:PASSED";
    	bufferWritter.write(s);
    	bufferWritter.write("\n");
    	bufferWritter.close();
    	
    }
    else 
    {
    	String s="ICA99Test:FAILED";
    	bufferWritter.write(s);
    	bufferWritter.write("\n");
    	bufferWritter.close();
    }
    
    //assertEquals("guest", user);
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
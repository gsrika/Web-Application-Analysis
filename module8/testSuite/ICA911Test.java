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

public class ICA911Test {
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
    driver.get(baseUrl + "/bookstore/OrdersRecord.jsp?member_id=1&item_id=21&quantity=1&FormName=Orders&FormAction=delete&Trn_item_id=&Trn_member_id=&PK_order_id=4");
    driver.findElement(By.name("Login")).clear();
    driver.findElement(By.name("Login")).sendKeys("admin");
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.get(baseUrl+"/bookstore/OrdersRecord.jsp?member_id=1&item_id=21&quantity=1&FormName=Orders&FormAction=delete&Trn_item_id=&Trn_member_id=&PK_order_id=4");
                                                                                  
    File file=new File("/home/srikanth/gdrive/WorkSpace/ICA9/TestStatus.txt");
    if(!file.exists()){
		file.createNewFile();
	}
    FileWriter fileWritter = new FileWriter(file,true);
    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    
    
    
    String register=driver.findElement(By.xpath("//tr[2]/td/font")).getText();
   
    
    if(!"java.sql.SQLException: Can not issue empty query.".equals(register))
    {
    	String s="ICA911Test:PASSED";
    	bufferWritter.write(s);
    	bufferWritter.write("\n");
    	bufferWritter.close();
    	
    }
    else 
    {
    	String s="ICA911Test:FAILED";
    	bufferWritter.write(s);
    	bufferWritter.write("\n");
    	bufferWritter.close();
    }
    
   
   // assertEquals("java.sql.SQLException: Can not issue empty query.",register );
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
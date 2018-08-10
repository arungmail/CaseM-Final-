 

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateUser {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUser() throws Exception {
    driver.get(ServerCredential.baseUrl + "/");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(ServerCredential.admin);
    driver.findElement(By.id("pas")).clear();
    driver.findElement(By.id("pas")).sendKeys(ServerCredential.adminPassword);
    driver.findElement(By.id("buttonDisable")).click();
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.linkText("User")).click();
    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(UserDetails.email);
    driver.findElement(By.id("shortName")).clear();
    driver.findElement(By.id("shortName")).sendKeys(UserDetails.shortName);
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys(UserDetails.firstName);
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys(UserDetails.lastName);
    driver.findElement(By.id("passwordReminder")).clear();
    driver.findElement(By.id("passwordReminder")).sendKeys(UserDetails.passwordReminder);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(UserDetails.password);
    driver.findElement(By.xpath("(//input[@id='password'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@id='password'])[2]")).sendKeys(UserDetails.password);
    driver.findElement(By.name("isEmailVerified")).click();
    new Select(driver.findElement(By.id("requires"))).selectByVisibleText(UserDetails.language);
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    driver.findElement(By.linkText("Admin")).click();
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

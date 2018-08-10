
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Comment {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testComment() throws Exception {
		driver.get(ServerCredential.baseUrl + "/");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(UserDetails.email);
		driver.findElement(By.id("pas")).clear();
		driver.findElement(By.id("pas")).sendKeys(UserDetails.password);
		driver.findElement(By.id("buttonDisable")).click();
	List<List<HSSFCell>> data = selectSheet.getSheetData(8);
	for (int i = 1; i < data.size(); i++) {
	    driver.findElement(By.linkText("Base")).click();
	    driver.findElement(By.linkText("Comments")).click();
	    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
		List list = data.get(i);
		for (int j = 0; j < list.size() - 1; j++) {
			HSSFCell lang = (HSSFCell) list.get(list.size() - 1);
			new Select(driver.findElement(By.id("switch-language")))
					.selectByVisibleText(lang.getStringCellValue());
			HSSFCell cell = (HSSFCell) list.get(j);
			switch (j) {
			case 0:
				new Select(driver.findElement(By.id("parentCase_id")))
						.selectByVisibleText(cell.getStringCellValue());
				break;
			case 1:driver.findElement(By.id("creationDate")).clear();
			     		driver.findElement(By.id("creationDate")).sendKeys(
						cell.getDateCellValue() + "");
				break;
			case 2:driver.findElement(By.id("requires")).clear();
		    driver.findElement(By.id("requires")).sendKeys(cell.getStringCellValue());break;
			default:
				break;
			}
		}
		driver.findElement(By.cssSelector("input.btn.btn-default")).click();
	}
	 driver.findElement(By.linkText("Base")).click();
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

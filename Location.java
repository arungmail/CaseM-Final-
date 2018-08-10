import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Location {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLocation() throws Exception {
		driver.get(ServerCredential.baseUrl + "/");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(UserDetails.email);
		driver.findElement(By.id("pas")).clear();
		driver.findElement(By.id("pas")).sendKeys(UserDetails.password);
		driver.findElement(By.id("buttonDisable")).click();
		List<List<HSSFCell>> data = selectSheet.getSheetData(9);
		for (int i = 1; i < data.size(); i++) {
			driver.findElement(By.linkText("Base")).click();
			driver.findElement(By.linkText("Locations")).click();
			driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
			List list = data.get(i);
			for (int j = 0; j < list.size() - 1; j++) {
				HSSFCell lang = (HSSFCell) list.get(list.size() - 1);
				new Select(driver.findElement(By.id("switch-language")))
						.selectByVisibleText(lang.getStringCellValue());
				HSSFCell cell = (HSSFCell) list.get(j);
				switch (j) {
				case 0:
					driver.findElement(By.id("name")).clear();
					driver.findElement(By.id("name")).sendKeys(
							cell.getStringCellValue());

					break;
				case 1:
					driver.findElement(By.id("street")).clear();
					driver.findElement(By.id("street")).sendKeys(
							cell.getStringCellValue());
					break;
				case 2:
					driver.findElement(By.id("city")).clear();
					driver.findElement(By.id("city")).sendKeys(
							cell.getStringCellValue());
					break;
				case 3:driver.findElement(By.id("state")).clear();
				driver.findElement(By.id("state")).sendKeys(cell.getStringCellValue());
				break;
				case 4:new Select(driver.findElement(By.id("country_id")))
				.selectByVisibleText(cell.getStringCellValue()); break;
				case 5:driver.findElement(By.id("latitude")).clear();
				driver.findElement(By.id("latitude")).sendKeys(cell.getNumericCellValue()+"");break;
				case 6:driver.findElement(By.id("altitude")).clear();
				driver.findElement(By.id("altitude")).sendKeys(cell.getNumericCellValue()+"");	break;
				case 7:driver.findElement(By.id("longitude")).clear();
				driver.findElement(By.id("longitude")).sendKeys(cell.getNumericCellValue()+"");break;
				case 8:driver.findElement(By.id("ipAddress")).clear();
				driver.findElement(By.id("ipAddress")).sendKeys(cell.getStringCellValue());break;
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

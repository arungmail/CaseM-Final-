
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Contact {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCreate() throws Exception {
		driver.get(ServerCredential.baseUrl + "/");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(UserDetails.email);
		driver.findElement(By.id("pas")).clear();
		driver.findElement(By.id("pas")).sendKeys(UserDetails.password);
		driver.findElement(By.id("buttonDisable")).click();

		List<List<HSSFCell>> data = selectSheet.getSheetData(0);
		for (int i = 1; i < data.size(); i++) {
			driver.findElement(By.linkText("Contacts")).click();
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
					System.out.println(cell.getStringCellValue() + " : " + i);
					break;
				case 1:
					new Select(driver.findElement(By.id("requires")))
							.selectByVisibleText(cell.getStringCellValue());
					break;
				case 2:
					new Select(driver.findElement(By
							.name("newContactSubType.id")))
							.selectByVisibleText(cell.getStringCellValue());
					break;
				case 3:
					driver.findElement(By.id("email")).clear();
					driver.findElement(By.id("email")).sendKeys(
							cell.getStringCellValue());
					break;
				case 4:
					driver.findElement(By.id("address")).clear();
					driver.findElement(By.id("address")).sendKeys(
							cell.getStringCellValue());
					break;
				case 5:
					new Select(driver.findElement(By.id("country_id")))
							.selectByVisibleText(cell.getStringCellValue());
					break;
				case 6:
					new Select(driver.findElement(By.id("language_id")))
							.selectByVisibleText(cell.getStringCellValue());
					break;
				case 7:
					driver.findElement(By.id("telephone1")).clear();
					driver.findElement(By.id("telephone1")).sendKeys(
							cell.getStringCellValue());
					break;
				case 8:
					new Select(driver.findElement(By.id("typeTelephone1_id")))
							.selectByVisibleText(cell.getStringCellValue());
					break;
				default:
					break;
				}
			}
			System.out.println("Saving data");
			driver.findElement(By.cssSelector("input.btn.btn-default")).click();
		}
		driver.findElement(By.linkText("Contacts")).click();
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

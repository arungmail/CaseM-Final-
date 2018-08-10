import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Country {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCountry() throws Exception {
		driver.get(ServerCredential.baseUrl + "/");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(ServerCredential.admin);
		driver.findElement(By.id("pas")).clear();
		driver.findElement(By.id("pas")).sendKeys(ServerCredential.adminPassword);
		driver.findElement(By.id("buttonDisable")).click();
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.linkText("Country")).click();
		driver.findElement(
				By.xpath("//div[@id='main']/div[3]/div/div/div[2]/div/div/a"))
				.click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("USA");
		driver.findElement(By.cssSelector("input.btn.btn-default")).click();
		driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("England");
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

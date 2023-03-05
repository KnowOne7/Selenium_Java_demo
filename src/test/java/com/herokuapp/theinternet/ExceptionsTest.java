package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTest {

private WebDriver driver;
	
	@Parameters({"browser",})
	@BeforeMethod(alwaysRun = true)
	private void setup(@Optional("firefox") String browser) {
//		Create Driver 
		switch (browser) {
		case "chrome": {
//			System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		case "firefox":{
//			System.setProperty("webdriver.gecko.driver",  "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
			
		default:
			System.out.println("Unexpected value: " + browser);
//			System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		
//		Implicit wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@Test(priority = 1, enabled = true, groups = { "elementTest", "none" })
	public void NoSuchElement() {

		driver.get("https://practicetestautomation.com/practice-test-exceptions");
		
		driver.findElement(By.xpath("/html//button[@id='add_btn']")).click();
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement row_2_input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@type='text']")));
		
		Assert.assertTrue(row_2_input.isDisplayed(), "Failed to load is not displayed");
		  
	}

	@Test(priority = 1, enabled = true, groups = { "elementTest", "none" })
	public void ElementNotInteractableException() {

		driver.get("https://practicetestautomation.com/practice-test-exceptions");
		
		driver.findElement(By.xpath("/html//button[@id='add_btn']")).click();
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement row_2_input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@type='text']")));
		row_2_input.sendKeys("Sushi");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='rows']/div[3]/div[@class='row']/button[@id='save_btn']"))).click();
		
		String confirmation_message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rows']//div[@id='confirmation']"))).getText();
		
		Assert.assertTrue(confirmation_message.contains("Row 2 was saved"), "Failed to load is not displayed");
	}
	
	@Test(priority = 1, enabled = true, groups = { "elementTest", "none" })
	public void InvalidElementStateException() {

		driver.get("https://practicetestautomation.com/practice-test-exceptions");
		
		driver.findElement(By.xpath("/html//button[@id='edit_btn']")).click();
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row_1_input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='row1']/input[@value='Pizza']")));
		row_1_input.clear();
		row_1_input.sendKeys("Tacos");
		driver.findElement(By.xpath("/html//button[@id='save_btn']")).click();
		String confirmation_message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rows']//div[@id='confirmation']"))).getText();
				
		Assert.assertTrue(confirmation_message.contains("Row 1 was saved"), "Failed to load is not displayed");
		
	}
	
	@Test(priority = 1, enabled = true, groups = { "elementTest", "debug" })
	public void StaleElementReferenceException() {

		driver.get("https://practicetestautomation.com/practice-test-exceptions");
		
		WebElement visible_message_locator = driver.findElement(By.xpath("/html//p[@id='instructions']"));
		
		driver.findElement(By.xpath("/html//button[@id='add_btn']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		Boolean message_invisible = wait.until(ExpectedConditions.invisibilityOf(visible_message_locator));
		Assert.assertTrue(message_invisible, "Message is still visible");
				
	}
	
	@Test(priority = 1, enabled = true, groups = { "elementTest", "none" })
	public void TimeoutException() {

		driver.get("https://practicetestautomation.com/practice-test-exceptions");
		
		driver.findElement(By.xpath("/html//button[@id='add_btn']")).click();
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement row_2_input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@type='text']")));
		
		Assert.assertTrue(row_2_input.isDisplayed(), "Failed to load is not displayed");
		  
	}

	

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}
}

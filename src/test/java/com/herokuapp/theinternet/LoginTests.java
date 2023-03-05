package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class LoginTests {
	
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

	}
	
	
	@Parameters({"username", "password", "expected_message"})
	@Test(priority = 2, enabled = true, groups = { "negatiiveTests", "smokeTest" })
	public void invalidTest(String username, String password, String expected_message) {

//		String openUrl =  "https://the-internet.herokuapp.com/login"; 
		driver.get("https://the-internet.herokuapp.com/login");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	 	
		driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
	 	
	 	String invalid_user_msg = driver.findElement(By.xpath("/html//div[@id='flash']")).getText();
		Assert.assertTrue(invalid_user_msg.contains(expected_message), "invalid user message contains expected message");
		  
	}



	@Parameters({"username", "password", "expected_message"})
	@Test(priority = 1, enabled = true, groups = { "positiveTests", "smokeTest" })
	public void loginTest(String username, String password, String expected_message) {
		System.out.println("Starting login test");
		
//		System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
//      Open test page 
	    String openUrl =  "https://the-internet.herokuapp.com/login"; 
	    driver.get(openUrl);
	    System.out.println("Page is open");
	  
//	    Enter username  
	    driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
	  
//	    enter password 
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

//	    click login button //form[@id='login']/button[@class='radius']
	    driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
	  
//	    verification 
	    String confirm_url = "https://the-internet.herokuapp.com/secure";
	    String actual_url = driver.getCurrentUrl();
	    Assert.assertEquals(confirm_url, actual_url, "Actual URL is not as expected");
	  
//	    logout button is visible //div[@id='content'] //a[@href='/logout']/i[@class='icon-2x icon-signout'] 
	    WebElement logOutButton = driver.findElement(By.xpath("//div[@id='content'] //a[@href='/logout']/i[@class='icon-2x icon-signout']"));
	    Assert.assertTrue(logOutButton.isDisplayed(), "logout button is not visible");
	   
	  
//	    successful login message 
	    String successfullMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
	    String expectedMessage = expected_message;
	    Assert.assertTrue(successfullMessage.contains(expectedMessage),  "Login Message is not Displayed correctly");

  
  }
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}
}

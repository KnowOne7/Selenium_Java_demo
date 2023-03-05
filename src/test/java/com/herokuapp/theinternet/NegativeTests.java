package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Parameters({"username", "password", "expected_message"})
	@Test(priority = 1, enabled = true, groups = { "negativeTests", "smokeTest" })
	
	public void invalidTest(String username, String password, String expected_message) {

//		System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver",  "src/main/resources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		String openUrl =  "https://the-internet.herokuapp.com/login"; driver.get(openUrl);
		
//		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);

		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	 	
		driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
	 	
	 	String invalid_user_msg = driver.findElement(By.xpath("/html//div[@id='flash']")).getText();
		Assert.assertTrue(invalid_user_msg.contains(expected_message), "invalid user message contains expected message");
		
		driver.quit();
	}
	
//	
//	@Test(priority = 1, enabled = true, groups = { "negativeTests", "smokeTest" })
//	public void invalidUserTest() {
//
////		System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
////		WebDriver driver = new ChromeDriver();
//		
//		System.setProperty("webdriver.gecko.driver",  "src/main/resources/geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
//		
//		String openUrl =  "https://the-internet.herokuapp.com/login"; driver.get(openUrl);
//		
////		driver.manage().window().maximize();
//		
//		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
//		username.sendKeys("tom  smith");
//		
//		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
//	 	
//		driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
//	 	
//	 	String invalid_user_msg = driver.findElement(By.xpath("/html//div[@id='flash']")).getText();
//		Assert.assertTrue(invalid_user_msg.contains("Your username is invalid!"), "invalid user message contains expected message");
//		
//		driver.quit();
//	}
//	
//	@Test(priority=2, enabled = true, groups = { "negativeTests", "exampleTests" })
//	public void invalidPasswordTest() {
//
////		Create Driver 
//		System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		
////      Open test page 
//	    String openUrl =  "https://the-internet.herokuapp.com/login"; driver.get(openUrl);
//	    System.out.println("Page is open");
//	  
////	    maximize browser window 
//	    driver.manage().window().maximize();
//
////      Enter user name  
//	    WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
//	    username.sendKeys("tomsmith");
//	  
////	    enter incorrect password 
//	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("IncorrectSuperDuperPassword!");
//
////	    click login button 
//	    driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
//	  
//	    String invalid_user_msg = driver.findElement(By.xpath("/html//div[@id='flash']")).getText();
//	    Assert.assertTrue(invalid_user_msg.contains("Your password is invalid!"), "invalid user message contains expected message");
//	
//	    driver.quit();
//	}
//	
//	
}

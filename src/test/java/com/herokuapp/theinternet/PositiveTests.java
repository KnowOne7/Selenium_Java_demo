
package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting login test");
//		Create Driver 
		System.setProperty("webdriver.chrome.driver",  "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//    Open test page "https://the-internet.herokuapp.com/login"
	  String openUrl =  "https://the-internet.herokuapp.com/login"; driver.get(openUrl);
	  System.out.println("Page is open");
	  
//	  sleep(3000);
	  
//	  maximize browser window 
	  driver.manage().window().maximize();
	  
//	  sleep(2000);
	  
//	  Enter username  
	  // $x("//input[@id='username']") in console to test XPATH
	  WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	  username.sendKeys("tomsmith");
	  
//	  enter password 
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");

//	  click login button //form[@id='login']/button[@class='radius']
	  driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']")).click();
	  
//	  verification 
	  String confirm_url = "https://the-internet.herokuapp.com/secure";
	  String actual_url = driver.getCurrentUrl();
	  Assert.assertEquals(confirm_url, actual_url, "Actual URL is not as expected");
	  
//	  logout button is visible //div[@id='content'] //a[@href='/logout']/i[@class='icon-2x icon-signout'] 
	  WebElement logOutButton = driver.findElement(By.xpath("//div[@id='content'] //a[@href='/logout']/i[@class='icon-2x icon-signout']"));
	  Assert.assertTrue(logOutButton.isDisplayed(), "logout button is not visible");
	  
	  
//	  successful login message 
	  String successfullMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
	  String expectedMessage = "You logged into a secure area!";
//	  Assert.assertEquals(successfullMessage.getText(), expectedMessage,  "Login Message is not Displayed correctly");
	  Assert.assertTrue(successfullMessage.contains(expectedMessage),  "Login Message is not Displayed correctly");
	  
//	  Close Browser
	  sleep(1000);
	  driver.quit();
  
  }

	private void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
 

/*
 * package com.herokuapp.theinternet;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import org.testng.annotations.Test;
 * 
 * 
 * @Test public class PositiveTests {
 * 
 * @Test public void loginTest() { System.out.println("Starting loginTest");
 * 
 * // Create driver System.setProperty("webdriver.chrome.driver",
 * "src/main/resources/chromedriver.exe"); WebDriver driver = new
 * ChromeDriver();
 * 
 * // sleep for 3 seconds sleep(3000);
 * 
 * // maximize browser window driver.manage().window().maximize();
 * 
 * // open test page String url = "http://the-internet.herokuapp.com/login";
 * driver.get(url); System.out.println("Page is opened.");
 * 
 * // sleep for 2 seconds sleep(2000);
 * 
 * // enter username
 * 
 * // enter password // click login button
 * 
 * // verificatins: // new url // logout button is visible // succesful login
 * message
 * 
 * 
 * // Close browser driver.quit(); }
 * 
 * private void sleep(long m) { try { Thread.sleep(m); } catch
 * (InterruptedException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } }
 * 
 * }
 */
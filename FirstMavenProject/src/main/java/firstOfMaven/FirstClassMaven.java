package firstOfMaven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstClassMaven {
	 
	WebDriver driver;
	@BeforeTest
	public void browser() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
    
	@Test(dataProvider="testData")
	public void login(String username, String password) throws InterruptedException {
		
		driver.get("http://103.226.185.90//uth/gadgetsgallery/catalog/");
		driver.findElement(By.linkText("log yourself in")).click();
		driver.findElement(By.name("email_address")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("tdb1")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log Off")).click();
		driver.findElement(By.id("tdb1")).click();
	}
	
	@DataProvider
	public Object[][] testData() {
		
		Object[][] data= new Object[3][2];
		
		data[0][0]="demo@unicodetechnologies.in";
		data[0][1]="unicode";
		
		data[1][0]="demo1@unicodetechnologies.in";
		data[1][1]="unicode1";
		
		data[2][0]="demo2@unicodetechnologies.in";
		data[2][1]="unicode2";
				
		return data;		
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}

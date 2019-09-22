package extentReportPkg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FirstClassMavenWitReport {
	ExtentHtmlReporter reporter = new  ExtentHtmlReporter(System.getProperty("user.dir")+"//src//main//resources//reports//Report.html");
	ExtentReports reports=new ExtentReports();
	ExtentTest test=reports.createTest("FirstClassMavenWitReport");
	WebDriver driver;
	@BeforeTest
	public void browser() {
		reports.attachReporter(reporter);
		reports.setSystemInfo("Environment", "Beta");
		reports.setSystemInfo("Tester", "Jack");
		reports.setSystemInfo("Date", "15/09/2019");
		
		
		driver = new FirefoxDriver();
		test.log(Status.INFO, "Firefox browser launched");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(Status.INFO, "Browser window maximized");
	}

	@Test(dataProvider="testData")
	public void login(String username, String password) throws InterruptedException {
		
		driver.get("http://103.226.185.90//uth/gadgetsgallery/catalog/");
		test.log(Status.INFO, "URL loaded");
		
		driver.findElement(By.linkText("log yourself in")).click();
		test.log(Status.INFO, "Home page displayed");
		
		driver.findElement(By.name("email_address")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		test.log(Status.INFO, "Username and Password entered");
		
		driver.findElement(By.id("tdb1")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log Off")).click();
		test.log(Status.INFO, "Login Successful");
		
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
		test.log(Status.INFO, "Browser closed and test done");
		test.log(Status.PASS, "Everything is fine...!");
		reports.flush();
	}
}

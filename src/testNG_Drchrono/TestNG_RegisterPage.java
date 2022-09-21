package testNG_Drchrono;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_RegisterPage {
	
	WebDriver driver;
	
//	@BeforeMethod
//	public void setUp(){
//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\sihik\\Desktop\\selBrowserDrives\\chromedriver_win32\\chromedriver.exe");
//    driver = new ChromeDriver();
//    driver.manage().window().maximize();
//   // driver.manage().deleteAllCookies();
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//    driver.get("https://app.drchrono.com");
//    String mpHandle = driver.getWindowHandle();
//	}
	
		
  @Test
  public void f() {
	  
//	  ChromeOptions options = new ChromeOptions();                                 //this method is used for browser pop ups atthe home page
//	  options.addArguments("--disable-notifications");
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\sihik\\Desktop\\selBrowserDrives\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String Url = "https://www.drchrono.com/";
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));                          //implicity wait is used to find the element
		String mpHandle = driver.getWindowHandle();
		
		String actualMainPg =driver.getCurrentUrl();
		String expectedMainPg = Url;
//		if(actualMainPg.equals(expectedMainPg))
//		{
//			System.out.println("application launch is passed");
//		}else
//		{
//			System.out.println("application launch is failed");
//		}
	    Assert.assertEquals(actualMainPg, expectedMainPg);            //instead if else  we can use assert
//		
			
		WebElement log = driver.findElement(By.xpath("//*[@id='navigation-bar']/div[1]/div/ul/li[4]/a/strong"));
		System.out.println(log.isEnabled() + " -->log in link is enabled");
		log.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));                              //explicit wait is used to load or wait to find the element
		wait.until(ExpectedConditions.urlContains("https://app.drchrono.com/accounts/login/"));
		
		
		//enter username
		WebElement uName = driver.findElement(By.xpath("//*[@id='username']"));
		uName.sendKeys("nisha2022");
		
		//enter password
		WebElement pWord = driver.findElement(By.id("password"));
		pWord.sendKeys("learning@22practice");
		
		//click on login button
		WebElement logIn= driver.findElement(By.id("login"));
		System.out.println(logIn.isEnabled() + " -->log in button is enabled");
		logIn.click();
		
		wait.until(ExpectedConditions.urlContains("https://nisha2022.drchrono.com/dashboard"));     
		
		WebElement trialPopUpClose = driver.findElement(By.xpath("//div[@title='Stop Walk-thru']"));         // use this when its not alert
//		trialPopUpClose.click();     
	    JavascriptExecutor js = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
		js.executeScript("arguments[0].click()", trialPopUpClose);
		
		WebElement patientsMenu =driver.findElement(By.xpath("//*[@id='toolbar']/div/div[1]/div/ul/li[3]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(patientsMenu).perform();
		System.out.println(patientsMenu.isDisplayed()  + " -->patient menu option is displayed");
		
		WebElement patientList = driver.findElement(By.xpath("//*[@id=\"toolbar\"]/div/div[1]/div/ul/li[3]/ul/li[2]/a"));  //  //a[text()='Patients'] --> u can write ur own xpath when xpath is not static if its dynamic u can write ur own xpath
		patientList.click();
		String actualPatientPg = driver.getCurrentUrl();
		String expectedPatientPg = "https://nisha2022.drchrono.com/patients/?page=1&query=&phone=&sort_column=Chart%20ID&sort_order=asc&";
//		if(actualPatientPg.equals(expectedPatientPg))
//		{
//			System.out.println("patient list is selected");
//		}else
//		{
//			System.out.println("patient list is not selected");
//			
//		}
		Assert.assertEquals(actualPatientPg, expectedPatientPg);
		Reporter.log("Patient list is selected");
		
		wait.until(ExpectedConditions.urlContains("https://nisha2022.drchrono.com/patients/"));                   //--->u can copy this url until patients bcoz it says contains --->https://nisha2022.drchrono.com/patients/?page=1&query=&phone=&sort_column=Chart%20ID&sort_order=asc&
		
		WebElement addNewPat = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/a[1]"));   //--->ur own xpath--> //a[@href='/patients/new/']
		System.out.println(addNewPat.isEnabled() + " -->add new patient button");
		addNewPat.click();
		
		 Set<String> whs= driver.getWindowHandles(); 
		 
		 for(String wh : whs)
		 {
			if(driver.switchTo().window(wh).getCurrentUrl().contains("https://nisha2022.drchrono.com/patients/new/"))
			{
				WebElement fName = driver.findElement(By.id("id_first_name"));      // we can write inside if or we can write outside if u can see that in registrationpage1 i wrote outside after window changed either ways u an write
				fName.sendKeys("Zike");
				WebElement lName = driver.findElement(By.id("id_last_name"));
				lName.sendKeys("Hurley");
				WebElement S = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div[6]/input[1]"));   // ur own xpath--->//input[@value='save']
				System.out.println(S.isEnabled() + " save button");                                  
				JavascriptExecutor jse = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
				jse.executeScript("arguments[0].click()", S);
				driver.close();
			} 
		 }
		
		 driver.switchTo().window(mpHandle);
		 System.out.println(driver.getTitle());
			
		 WebElement pSearch = driver.findElement(By.id("id_patient_search_name"));
		 pSearch.sendKeys("Zike");
			
		 WebElement searchBtn = driver.findElement(By.xpath("//*[@id='content']/div[3]/div[1]/div[1]/input[2]"));
		 searchBtn.click();
		    
		 WebElement chartId = driver.findElement(By.linkText("HUZI000001"));
		 chartId.click();
		 
//		 WebElement trialPopUpClose2 = driver.findElement(By.xpath("//*[@id=\"warning\"]/div[3]/button"));         // use this when its not alert
//		// trialPopUpClose2.click(); 
//		 JavascriptExecutor js1 = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
//		 js1.executeScript("arguments[0].click()",  trialPopUpClose2);
//		 
		  WebElement hPhone = driver.findElement(By.id("id_home_phone"));
//		    hPhone.clear();
//		    hPhone.click();
		    hPhone.sendKeys("(844)569-8628");
		    

		    WebElement cPhone = driver.findElement(By.id("id_cell_phone"));
//		    cPhone.clear();
//		    cPhone.click();
		    cPhone.sendKeys("(650)215-6343");
		    
		    WebElement sDemoGra = driver.findElement(By.xpath("//*[@id='content1']/div[3]/div[6]/div/input[1]"));
		    System.out.println(sDemoGra.isEnabled() + " save demo");
		  //  sDemoGra.click();	  
		    JavascriptExecutor js2 = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
			js2.executeScript("arguments[0].click()", sDemoGra);
	
  }
}

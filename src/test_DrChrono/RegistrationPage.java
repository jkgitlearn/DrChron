package test_DrChrono;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	public static void main(String[] args) throws InterruptedException  {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sihik\\Desktop\\selBrowserDrives\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		String Url = "https://app.drchrono.com";
		driver.get(Url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
			
		WebElement log = driver.findElement(By.xpath("//*[@id='navigation-bar']/div[1]/div/ul/li[4]/a/strong"));  //        //strong[text()='Log In']-->u can use ur own path writing like this when xpath is not good given in inspect
		System.out.println(log.isEnabled() + " log in link");
		log.click();
		
		//enter username
		WebElement uName = driver.findElement(By.xpath("//*[@id='username']"));
		uName.sendKeys("nisha2022");
		
		//enter password
		WebElement pWord = driver.findElement(By.id("password"));
		pWord.sendKeys("learning@22practice");
		
		//click on login button
		WebElement logIn= driver.findElement(By.id("login"));
		System.out.println(logIn.isEnabled() + " log in button");
		logIn.click();
		
		WebElement trialPopUpClose = driver.findElement(By.xpath("//div[@title='Stop Walk-thru']"));         // use this when its not alert
		trialPopUpClose.click();                                              
		
		
		WebElement p = driver.findElement(By.xpath("//*[@id='toolbar']/div/div[1]/div/ul/li[3]/a"));
		p.click();		
	
//		WebElement cform = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/ul/li[3]/ul/li[3]/a"));
//      Select dropDown = new Select(cform);                           //  we cant use this bcoz its not select elemnet its a mouse over element
//      dropDown.selectByVisibleText("Consent Forms");
//      cform.click();
		
	
		//Store the ID of the original window
		String windowId = driver.getWindowHandle();                // return ID of the single browser window that is parent/original window
		System.out.println("parent id---> " + windowId); 
		
		//click on add new patient button
		WebElement newPat = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/a[1]"));
		System.out.println(newPat.isEnabled() + " ---->add new patient button");
		newPat.click();
		
		 Set<String> whs= driver.getWindowHandles(); 
		 
		 for(String wh : whs)
	 {	 	
		 //System.out.println(wh);                                                  // these will get each window id's in console	
		 String url = driver.switchTo().window(wh).getCurrentUrl();
		 System.out.println("url ----> " + url);	 
//	 } 
		 if(driver.switchTo().window(wh).getCurrentUrl().contains("https://nisha2022.drchrono.com/patients/new/"))
	  {	 
        WebElement fName = driver.findElement(By.id("id_first_name"));
		fName.sendKeys("Mike");
		
		WebElement nName = driver.findElement(By.id("id_nick_name"));
		nName.sendKeys("John");
		
		WebElement mName = driver.findElement(By.id("id_middle_name"));
		mName.sendKeys("j");
		
		WebElement lName = driver.findElement(By.id("id_last_name"));
		lName.sendKeys("Hurley");
		
		
		WebElement S = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div[6]/input[1]"));
		System.out.println(S.isEnabled() + " save button");
//		S.click();
				
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));                      //explicit
//		 wait.until(ExpectedConditions.urlContains("https://kt2023.drchrono.com/patients/new/"));
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("S"))).click();			
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
		jse.executeScript("arguments[0].click()", S);
		
			driver.close();	

	  }
				 
	}	
		driver.switchTo().window(windowId);
		System.out.println(driver.getTitle());
		
		WebElement pSearch = driver.findElement(By.id("id_patient_search_name"));
		pSearch.sendKeys("Mike");
		
		WebElement sBtn = driver.findElement(By.xpath("//*[@id='content']/div[3]/div[1]/div[1]/input[2]"));
	    sBtn.click();
	    
	    WebElement cId = driver.findElement(By.linkText("HUMI000001"));
	    cId.click();
	    
//	    Alert alert = driver.switchTo().alert();             //simple alert or alert 
//		String actualsAlertmsg = alert.getText();
//		System.out.println(actualsAlertmsg);
//	    alert.accept();
	    
	   		
		WebElement  palertTrigger = driver.findElement(By.xpath("//*[@id=\"warning\"]/div[3]/button"));
//		palertTrigger.click();
//		driver.switchTo().alert().accept();                                // prompt alert, its not an alert so, cant use alert method
//		alert.dismiss();  
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
		js1.executeScript("arguments[0].click()",  palertTrigger);

	    WebElement hPhone = driver.findElement(By.id("id_home_phone"));
	    hPhone.clear();
	    hPhone.click();
	    hPhone.sendKeys("(844)569-8628");
	    

	    WebElement cPhone = driver.findElement(By.id("id_cell_phone"));
	    cPhone.clear();
	    cPhone.click();
	    cPhone.sendKeys("(650)215-6343");
	    
	    WebElement sDemo = driver.findElement(By.xpath("//*[@id='content1']/div[3]/div[6]/div/input[1]"));
	    System.out.println(sDemo.isEnabled() + " save demo");
	  //  sDemo.click();	  
	    
	    JavascriptExecutor js = (JavascriptExecutor)driver;                                 // when u get an not clickable exception and if webdriver wait didnt wotk we use java script code
		js.executeScript("arguments[0].click()", sDemo);
		
		
        

	}

}

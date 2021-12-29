package iti_normal_auto;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GUI_testing {
	
	ChromeDriver driver ;
	
	String netlink ="https://the-internet.herokuapp.com";

	
	//driver property///
	
	@BeforeClass
	public void driver_pre()
	{
		String chromepath =System.getProperty("user.dir") +"\\resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver"  , chromepath);
		driver = new ChromeDriver(); 

	
	}
	
	
	//google test case ///
	
	@Test(priority = 0)
	public void First_Test()
	{
		driver.navigate().to("https://www.google.com/ncr");
		WebElement searchbar= driver.findElement(By.name("q"));
		searchbar.clear();
		searchbar.sendKeys("selenium webdriver");
		searchbar.submit();
		
		WebElement third_res =driver.findElement(By.xpath("//*[@id=\"rso\"]/div[3]/div/div"));
		
		String third_text = third_res.getText();
		
		assertTrue(third_res.getText().contains("What is Selenium WebDriver"));
		System.out.println(third_text);
		
	}
	
	//upload files test case ///

	@Test (priority = 1)
	public void secondt_Test()
	{
		
		String imgname="clean code book summery.jpg";
		String imgpath= System.getProperty("user.dir")+"/uploads/"+imgname;


		driver.navigate().to(netlink);
		
		@SuppressWarnings("deprecation")
		
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		
		wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"content\"]/ul/li[18]/a")));

		WebElement fileuploade_link = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[18]/a"));
		fileuploade_link.click();
		
		WebElement fileuploader = driver.findElement(By.id("file-upload"));
		fileuploader.sendKeys(imgpath);

		WebElement filesubmit = driver.findElement(By.id("file-submit"));
		filesubmit.click();

		WebElement uploadedfiles = driver.findElement(By.id("uploaded-files"));

		System.out.println(uploadedfiles.getText());

		Assert.assertEquals(imgname,uploadedfiles.getText());
		

	}
	
	//Dynamic Loading test case ///
	
	@SuppressWarnings("deprecation")
	@Test(priority = 2)
	public void third_test() throws InterruptedException
	{
		
		driver.navigate().to(netlink);
		
		WebDriverWait wait2 = new WebDriverWait(driver, 10);

		WebElement dynamicload = driver.findElement(By.linkText("Dynamic Loading"));
		dynamicload.click();
		
		WebElement example =driver.findElement(By.linkText("Example 2: Element rendered after the fact"));
		example.click();
		
		
		wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("start")));
		
		WebElement start_button = driver.findElement(By.xpath("//*[@id=\"start\"]/button"));
		start_button.click();
		
		
		//wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("loading")));
		
		wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"finish\"]/h4")));
		
		WebElement finish_text = driver.findElement(By.xpath("//*[@id=\"finish\"]/h4"));
		
		String get_finish_text =finish_text.getText();
		
		
		Assert.assertEquals(get_finish_text,"Hello World!");
		
		
	}
	
	//Dropdown test case ///
	@Test(priority = 3)
	
	public void dropdowntest() throws InterruptedException 
	{
		
		driver.navigate().to(netlink);
		
		WebElement dropdownlink = driver.findElement(By.linkText("Dropdown"));
		
		dropdownlink.click();
		
		
		Select dropbox =new Select( driver.findElement(By.id("dropdown")));
		
		Assert.assertFalse(dropbox.isMultiple());
		
		Assert.assertEquals(3 , dropbox.getOptions().size());
		
		dropbox.selectByVisibleText("Option 2");
		
		Assert.assertEquals("Option 2", dropbox.getFirstSelectedOption().getText());
		
		
		Thread.sleep(2000);
		
		
		
		
	}
	
	//Checkboxes test case ///
	
	@Test(priority = 4)
	
	public void checklisttest() throws InterruptedException 
	{
		
		driver.navigate().to(netlink);
		
		WebElement checklistlink = driver.findElement(By.linkText("Checkboxes"));
		
		checklistlink.click();
		
		WebElement check1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
		check1.click();
		Thread.sleep(2000);
		
		WebElement check2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
		
		if(check2.isSelected())
		{
			check2.click();
			
		}
		
		Thread.sleep(2000);
		
		
		
		
	}
	
	//close the driver  ///

	@AfterClass
	public void quit()
	{
		driver.quit();
	}


}

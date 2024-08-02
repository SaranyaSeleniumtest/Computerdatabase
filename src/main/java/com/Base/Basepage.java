package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Basepage {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static Select sel;


	public static void Selectcalenderdate(By locator,String expmon,String expyear,String expdate) {
		String monthyear = driver.findElement(locator).getText();
		System.out.println(monthyear);

		if(expmon.equals("February")&& (Integer.parseInt(expdate)>29)){
			System.out.println("Wrong date entered "+expdate);
			return;
		}

		if(Integer.parseInt(expdate)>31){
			System.out.println("Wrong date entered "+expdate);
			return;
		}
		while (!(splitdate(monthyear)[0].equals(expmon)&&(splitdate(monthyear)[1].equals(expyear)))){
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthyear = driver.findElement(locator).getText();
		}

		try {
			driver.findElement(By.xpath("//a[text()='"+expdate+"']")).click();
		}catch(Exception e) {
			System.out.println("Wrong date entered "+e.getMessage());
		}
	}

	public static void Webdriverwait(WebElement ele) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public static String[] splitdate(String monthyear) {
		String[] my = monthyear.split(" ");
		return my;

	}

	public static void doclick(WebElement ele) {
		if(ele.isEnabled()) {
			ele.click();
			System.out.println(ele+ " element is clicked");
		}
	}


	public static void enterval(WebElement ele,String val) {
		try {
			ele.clear();
			ele.sendKeys(val);

		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	
	public WebElement getelement(WebElement ele) {
		return ele;
	}
	
	
	
	public String getmessage(WebElement ele) {
		try {
		String txt=ele.getText();
		return txt;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage()+"check element");
		}
		return null;
	}
	
	
	public WebElement getElementByXpathContainsText(String xpath)
	{
		try {
			return	driver.findElement(By.xpath(xpath));
		}catch(Exception e){
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
		return null;


	}

	public static void tc1() {
		driver.findElement(By.xpath("//div[contains(@class,'h_menu_drop_button')]//a")).click();
		driver.findElement(By.xpath("//div[@role='complementary']//ul[@class='menu']//a[normalize-space()='FLIGHTS']")).click();
	}
	public static void handlewindows() {
		//https://youtu.be/zlcQTTjsgGI?si=wZV1_oxmKN5otoSi
		String win1title = driver.getTitle();
		System.out.println(win1title);
		Set<String> windowHandles = driver.getWindowHandles();
		Object[] array = windowHandles.toArray();
		String win1=array[0].toString();
		String win2=array[1].toString();
		driver.switchTo().window(win2);
		System.out.println("title of window2"+driver.getTitle());
		driver.switchTo().window(win1);
		System.out.println("title of window1"+driver.getTitle());
	}

	public static void handlewindows1() {
		String win1title = driver.getTitle();
		System.out.println(win1title);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> win=new ArrayList<String>(windowHandles);

		String win1=win.get(0);
		String win2=win.get(1);
		driver.switchTo().window(win2);
		System.out.println("title of window2  "+driver.getTitle());
		driver.switchTo().window(win1);
		System.out.println("title of window1  "+driver.getTitle());
	}

	public static String getvaluefromTable(int colcompare,String condition,int getcol) {
		//get row cnt
		boolean valfound=false;
		String txt=null;
		List<WebElement> rowele = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
		int rowcnt = rowele.size();
		for(int i=2 ;i<=rowcnt;i++) {
			String exptext = driver.findElement(By.xpath("//table[@id='customers']//tbody//tr["+i+"]//td["+colcompare+"]")).getText();
			if(exptext.equalsIgnoreCase(condition)) {
				txt=driver.findElement(By.xpath("//table[@id='customers']//tbody//tr["+i+"]//td["+getcol+"]")).getText();
				valfound=true;
				break;
			}
		}
		return txt;
	}

	public static boolean performactionTable(int colcompare,String condition,int getcol,String tagtype) {
		//get row cnt
		boolean valfound=false;
		List<WebElement> rowele = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
		int rowcnt = rowele.size();
		for(int i=2 ;i<=rowcnt;i++) {
			String exptext = driver.findElement(By.xpath("//table[@id='customers']//tbody//tr["+i+"]//td["+colcompare+"]")).getText();
			if(exptext.equalsIgnoreCase(condition)) {

				driver.findElement(By.xpath("//table[@id='customers']//tbody//tr["+i+"]//td["+getcol+"]//"+tagtype)).click();

				valfound=true;

				break;
			}
		}
		Assert.fail("Value not found in webtable");
		return valfound;

	}


	public static void selectdrop(WebElement ele,String val) {
		sel= new Select(ele);
		try {
			sel.selectByValue(val);
			System.out.println("Selected value is : "+sel.getFirstSelectedOption().getText());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

	}

	public static void selectdrop(WebElement ele,int val) {
		sel= new Select(ele);
		try {
			sel.selectByIndex(val);
			System.out.println("Selected value by index is : "+val);
		}catch(Exception e) {
			e.getMessage();
		}

	}

	public static void selectdrop(WebElement ele,String visibletxt,String isMultiple) {
		sel= new Select(ele);
		if (isMultiple.equals("No")) {
			try {
				sel.selectByVisibleText(visibletxt);
				System.out.println("Selected value by visible text is : "+visibletxt);
			}catch(Exception e) {
				e.getMessage();
			}
		}	
		else if(sel.isMultiple() && isMultiple.equals("Yes")) {
			List<WebElement> options = sel.getOptions();
			System.out.println("Selected multiple options : ");
			try {
				for (WebElement opt : options) {
					sel.selectByVisibleText(opt.getText());
					System.out.println(opt.getText());

				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}



	public static void handleradio(String attribute) {
		try {
			driver.findElement(By.id(attribute)).click();
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}


	public static boolean handlebootstrapdrop(){
		boolean clicked=false;
		String xpath="//ul[@class='dropdown-menu']//li/a";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for (WebElement ele : elements) {
			if(ele.getText().equalsIgnoreCase("CSS")){
				ele.click();
				clicked=true;
				break;
			}

		}
		//Assert.fail();
		return clicked;

	}

	public static void mouseactions(WebElement ele1,WebElement ele2) throws Exception {
		Actions act= new Actions(driver);
		try {
			act.moveToElement(ele1).moveToElement(ele2).click().perform();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @Method name: Method to click on radio button
	 * @param element: pass list of radio button
	 * @param value: pass the value of radio button
	 */
	public void handleradiobutton(List<WebElement> element,String value) {
		for (WebElement opt : element) {
			if(opt.getText().equalsIgnoreCase(value)) {
				opt.click();
				break;
			}
			
		}
		
	}
	
	/**
	 * @Method name: Method to select on multiple checkboxes
	 * @param element pass list of checkbox 
	 * @param values pass the value of checkboxes using delimitter comma(,)
	 */
	public void handleCheckbox(List<WebElement> element,String values) {
	String[]array=values.split(",");
	for (String arr : array) {
		for (WebElement opt : element) {
			if(opt.getText().equalsIgnoreCase(arr)) {
				opt.click();
				break;
			}
			
		}
		
	}
	}
	
	public static void handleCheckbox(String attribute) {
		try {
			driver.findElement(By.id(attribute)).click();
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean validate() {
		return driver.findElement(By.id("code")).isSelected();
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}

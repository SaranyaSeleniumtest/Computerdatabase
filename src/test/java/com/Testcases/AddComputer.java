package com.Testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.Basepage;
import com.Pageobjects.AddComputerpage;
import com.Pageobjects.ComputerDatabasepage;

public class AddComputer extends TestBase {
	
	public static ComputerDatabasepage cdp;
	public static AddComputerpage acp;
	
	@Test(priority=1,enabled=false)
	public void TC002_validatepageheading()  {
		cdp= new ComputerDatabasepage(driver);
		 acp = cdp.click_addcomputer();
		//how hardcoded values handled
		Assert.assertEquals(acp.getheading(),"Add a computer","page heading mismatched");
	}
	
	@Test(priority=2,enabled=false)
	public void TC003_validatepagetitle() {
		acp = cdp.click_addcomputer();
		Assert.assertEquals(acp.gettitle(),"Computers database");

	}
	
	@Test(dataProvider ="data")
	public void TC004_AddComputer(String compname,String date1,String date2,String company) {
		cdp= new ComputerDatabasepage(driver);
		acp = cdp.click_addcomputer();
		Assert.assertEquals(acp.gettitle(),"Computers database");
		String compname1= acp.addcomputer(compname, date1,date2,company);
		acp.createcomp();
		Assert.assertEquals(cdp.getconfirmmsg(),"Computer "+compname1+" has been created");
	}
	
	@DataProvider
	public Object[][] data() {
		Object[][] obj =new Object[1][4];
		obj[0][0]= "Test";// only one row with 4 cols
		obj[0][1]="2000-11-20";
		obj[0][2]="2000-11-24";
		obj[0][3]="Tandy Corporation";
		return obj;
	
	}
}

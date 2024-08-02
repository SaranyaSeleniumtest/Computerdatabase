package com.Testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Base.Basepage;
import com.Pageobjects.ComputerDatabasepage;

public class DatabaseSearch extends TestBase {
	
	public static ComputerDatabasepage cdp;
	
	@Test
	public void TC001_DatabaseSearch() {
		cdp= new ComputerDatabasepage(driver);
		cdp.entercomputername("ASCI Blue Pacific");
		cdp.click_search();
		System.out.println("search completed");
		
		//add assert
		WebElement validatesearch = cdp.validatesearch("ASCI Blue Pacific");
		Assert.assertTrue(validatesearch.isDisplayed(),"database search failed");
	}

}

package com.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.Basepage;

public class ComputerDatabasepage extends Basepage {
	//find locators
	@FindBy(id="searchbox")
	WebElement txtsearch;
	
	@FindBy(id="searchsubmit")
	WebElement btnsubmit;
	
	@FindBy(id="add")
	WebElement lnk_addcomputer;
	
	@FindBy(xpath="//div[contains(@class,'alert')]/strong")
	WebElement confirm_msg;
	
	
//	@FindBy(xpath="//tbody//tr/child::td/a[text()="+val+"]")
//	WebElement 
	
	//constructor to intiantiate the driver
	public ComputerDatabasepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//actions on this page
	
	public void entercomputername(String name) {
		enterval(txtsearch, name);
	}
	
	public void click_search() {
		doclick(btnsubmit);
	}
	
	public AddComputerpage click_addcomputer() {
		doclick(lnk_addcomputer);
		return new AddComputerpage(driver);
		
	}
	
	public String getconfirmmsg() {
		//Computer testing has been created
		return getmessage(confirm_msg);
	}
	
	public WebElement validatesearch(String val) {
		String searchxpath="//tbody//tr/child::td/a[text()='"+val+"']";
		System.out.println(searchxpath);
		WebElement ele = getElementByXpathContainsText(searchxpath);
		return ele;
	}
}

package com.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.Basepage;

public class AddComputerpage extends Basepage {

	//add locators
	
	@FindBy(xpath="//section[@id='main']//h1")
	@CacheLookup
	WebElement heading;
	
	@FindBy(xpath="//input[@id='name']")
	@CacheLookup
	WebElement txt_name;
	
	@FindBy(xpath="//input[@id='introduced']")
	@CacheLookup
	WebElement txt_intro;
	
	@FindBy(name="discontinued")
	WebElement txt_discont;
	
	@FindBy(id="company")
	WebElement sel_comp;
	
	@FindBy(xpath="//input[@value='Create this computer']")
	WebElement btn_createcomp;
	//add constructor
	
	public AddComputerpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//add actions/methods to this page
	
	public String getheading() {
		String msg = heading.getText();
		return msg;
	}
	
	public String gettitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public void createcomp() {
		doclick(btn_createcomp);

	}
	
	public String addcomputer(String name,String intro,String date,String company) {
		enterval(txt_name,name);
		enterval(txt_intro, intro);
		enterval(txt_intro, date);
		selectdrop(sel_comp,company,"No");
		return name;
		
	}
}

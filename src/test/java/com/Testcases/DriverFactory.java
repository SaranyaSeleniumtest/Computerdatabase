package com.Testcases;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	ThreadLocal<WebDriver>tdriver=new ThreadLocal<>();
	public void setdriver(WebDriver driver) {
//		this.tdriver=driver;
	}

	public void getdriver() {
		
	}
}

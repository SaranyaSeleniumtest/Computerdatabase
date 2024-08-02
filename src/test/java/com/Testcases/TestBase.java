package com.Testcases;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.Base.Basepage;

public class TestBase extends Basepage 
{


	public  void initialize() throws Exception {
		String file=System.getProperty("user.dir")+"\\config.properties";
		FileInputStream fis= new FileInputStream(file);
		prop= new Properties();
		prop.load(fis);

		System.out.println(prop.getProperty("url"));

	}

	@Parameters({"browser"})
	@BeforeClass(alwaysRun = true)
	public  void setup(String browser) throws Exception {
		initialize();
		switch(browser.toLowerCase()) {
		case "chrome":

			driver= new ChromeDriver();

		case "edge":
			driver= new EdgeDriver();
		default: 

			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Assert.assertEquals(driver.getTitle(),prop.getProperty("title"),"title mismatched");
		}
	}



	//	public static void setup(String url) {
	//		driver= new ChromeDriver();
	//		driver.get(url);
	//		//	Assert.assertEquals(driver.getTitle(), "LetCode with Koushik");
	//		driver.manage().window().maximize();
	//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	}

	//@AfterClass
	public void teardown() {
		driver.quit();
	}

}

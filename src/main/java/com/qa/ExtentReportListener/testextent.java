package com.qa.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class testextent {
	
	public static void main(String[] args) {
		//1.report engine
		ExtentReports extent= new ExtentReports();
		ExtentSparkReporter file= new ExtentSparkReporter(".\\reports\\test.html");
		//2. add report path to engine
		extent.attachReporter(file);
		
		extent.flush();
		
	}

}

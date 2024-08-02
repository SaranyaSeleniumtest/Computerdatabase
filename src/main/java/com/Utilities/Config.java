package com.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	public static Properties prop;
	public static void initialize() throws Exception {
		String file=System.getProperty("user.dir")+"\\config.properties";
		FileInputStream fis= new FileInputStream(file);
		prop= new Properties();
		prop.load(fis);

		System.out.println(prop.getProperty("url"));

	}
}

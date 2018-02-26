package com.firesoon.drgs.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {

	public static Properties properties;
	static {
		properties = new Properties();
		InputStream input = null;
		InputStream in = null;
		try {
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf.properties");
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(input);
			properties.load(in);
			input.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

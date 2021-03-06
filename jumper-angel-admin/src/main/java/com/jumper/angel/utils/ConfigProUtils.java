package com.jumper.angel.utils;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigProUtils {
//	public static final String R_PATH = "/conf/file.properties";
	public static final String R_PATH = "/conf/file.properties";

	static Properties properties = null;
	static{
		String path = System.getenv("WEBAPP_APP_ADMIN_CONF")+R_PATH;
		if(!new File(path).exists()){
			throw new RuntimeException("not found file "+path);
		}
		Resource resource =new FileSystemResource(path);
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static String get(String key){
		return properties.getProperty(key);
	}
	public static void put(String key,String value){
		 properties.setProperty(key,value);
	}
}

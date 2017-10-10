/**
* PropertiesUtil.java
* 2016年8月29日-下午2:29:58
*  2016-深圳京柏医疗设备有限公司-版权所有
*
*/
package com.jumper.angel.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
*
* PropertiesUtil
*JumperNetInte
* 2016年8月29日 下午2:29:58
*
* @version 1.0.0
*/
public class PropertiesUtil {
	
	static Properties properties = null;
	static{
		Resource resource = new ClassPathResource("conf/file.properties");
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String get(String key){
		return properties.getProperty(key);
	}

	
	
	
	/**  
     * 保存属性到文件中  
     *   
     * @param pro  
     * @param file  
     */  
    public static void saveProperties( String file) {  
       
        FileOutputStream oFile = null;  
        try {  
            oFile = new FileOutputStream(file, false);  
            properties.store(oFile, "modify properties file");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (oFile != null) {  
                    oFile.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /**  
     * 修改属性文件  
     *   
     * @param key  
     * @param value  
     */  
    public static void updateProperties(String key, String value) {  
        // key为空则返回  
        if (key == null || "".equalsIgnoreCase(key)) {  
            return;  
        }  
        properties.put(key, value);  
    }  
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	System.out.println(PropertiesUtil.get("jpush.masterSecret"));
	}
}

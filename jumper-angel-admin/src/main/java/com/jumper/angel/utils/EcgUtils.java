package com.jumper.angel.utils;

import java.util.HashMap;
import java.util.Map;

public class EcgUtils {
	
	public static  String TOKEN=null;
	
	/**
	 * 更新心电token
	 * @return
	 */
	public static String setToken(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("entAccount", "jingbai");
		map.put("password", "1234");
		String result=HttpPostUtils.doPost(ConfigProUtils.get("kanjia_auth_url")+"/Auth.html", map);//获取token
		return result;
	}
	
	/**
	 * 如果容器内不存在token  则新生成一个token   并放到容器内
	 * @param servletContext
	 * @return
	 */
	public static String getToken(){
		if(TOKEN!=null){
			return TOKEN;
		}
		TOKEN=setToken();
		return TOKEN;
	}
	
	public static void main(String[] args) {
		String token = EcgUtils.getToken();
		System.out.println(token);
		//绑定设备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("devSerial", "3307051788191741");
		//访问绑定设备序列号接口 succeed
		String result = HttpPostUtils.doPost(ConfigProUtils.get("kanjia_auth_url") + "/AddDev.html", map);
		System.out.println(result);
	}
}

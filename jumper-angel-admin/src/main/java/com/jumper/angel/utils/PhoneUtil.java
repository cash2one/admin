package com.jumper.angel.utils;
/**电话号码加密处理*/
public class PhoneUtil {
	private PhoneUtil(){};

	/**转换为111****1111格式*/
	public static String format1(String phone) {
		if (phone!=null&&!"".equals(phone)) {
			String phones = "";
			char[] phoneChar = phone.toCharArray();
			if (phoneChar.length<10) {
				phones = phone;
			}else {
				for (int i = 0; i < phoneChar.length-8; i++) {
					phones += phoneChar[i];
				}
				phones += "****";
				for (int i = phoneChar.length-4; i < phoneChar.length; i++) {
					phones += phoneChar[i];
				}
			}
			return phones;
		}
		return "****";
	}
	/**转换为*******1111格式*/
	public static String format2(String phone) {
		if (phone!=null&&!"".equals(phone)) {
			String phones = "*******";
			char[] phoneChar = phone.toCharArray();
			if (phoneChar.length<10) {
				phones = phone;
			}else {
				for (int i = phoneChar.length-4; i < phoneChar.length; i++) {
					phones += phoneChar[i];
				}
			}
			return phones;
		}
		return "****";
	}
	/**转换为*1111格式*/
	public static String format3(String phone) {
		if (phone!=null&&!"".equals(phone)) {
			String phones = "*";
			char[] phoneChar = phone.toCharArray();
			if (phoneChar.length<10) {
				phones = phone;
			}else {
				for (int i = phoneChar.length-4; i < phoneChar.length; i++) {
					phones += phoneChar[i];
				}
			}
			return phones;
		}
		return "****";
	}
	/**转换为***1111格式*/
	public static String format4(String phone) {
		if (phone!=null&&!"".equals(phone)) {
			String phones = "***";
			char[] phoneChar = phone.toCharArray();
			if (phoneChar.length<10) {
				phones = phone;
			}else {
				for (int i = phoneChar.length-4; i < phoneChar.length; i++) {
					phones += phoneChar[i];
				}
			}
			return phones;
		}
		return "****";
	}
}

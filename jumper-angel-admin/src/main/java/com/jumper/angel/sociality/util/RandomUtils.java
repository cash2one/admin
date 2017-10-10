/**  
 * @Title: RandomUtils.java
 * @Package cn.com.jumper.anglesound.utils
 * @Description: TODO
 * @author huangzg
 * @date 2016-8-22
 */
package com.jumper.angel.sociality.util;

import java.util.Random;

/**
 * 随机数生成工具类
 * @Title: RandomUtils.java
 * @Package cn.com.jumper.anglesound.utils
 * @Description: TODO
 * @author huangzg
 * @date 2016-8-22
 */
public class RandomUtils {

	private final static Random rd = new Random();
	private static final String INT = "0123456789";
	private static final String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * 生成只含字母随机数
	 * @Title: RandomUtils.java
	 * @Package cn.com.jumper.anglesound.utils
	 * @Description: TODO
	 * @author huangzg
	 * @date 2016-8-22
	 */
	public static String randomStr(int length) {
		return random(length, RndType.STRING);
	}

	/**
	 * 生成只含数字随机数
	 * @Title: RandomUtils.java
	 * @Package cn.com.jumper.anglesound.utils
	 * @Description: TODO
	 * @author huangzg
	 * @date 2016-8-22
	 */
	public static String randomInt(int length) {
		return random(length, RndType.INT);
	}

	/**
	 * 生成含有数字及汉字随机数
	 * @Title: RandomUtils.java
	 * @Package cn.com.jumper.anglesound.utils
	 * @Description: TODO
	 * @author huangzg
	 * @date 2016-8-22
	 */
	public static String randomAll(int length) {
		return random(length, RndType.ALL);
	}

	/**
	 * 自定义格式生成随机数
	 * @Title: RandomUtils.java
	 * @Package cn.com.jumper.anglesound.utils
	 * @Description: TODO
	 * @author huangzg
	 * @date 2016-8-22
	 */
	private static String random(int length, RndType rndType) {
		StringBuilder s = new StringBuilder();
		char num = 0;
		for (int i = 0; i < length; i++) {
			if (rndType.equals(RndType.INT))
				num = INT.charAt(rd.nextInt(INT.length()));// 产生数字0-9的随机数
			else if (rndType.equals(RndType.STRING))
				num = STR.charAt(rd.nextInt(STR.length()));// 产生字母a-z的随机数
			else {
				num = ALL.charAt(rd.nextInt(ALL.length()));
			}
			s.append(num);
		}
		return s.toString();
	}

	public static enum RndType {
		INT, STRING, ALL
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.randomInt(11));
			System.out.println(RandomUtils.randomStr(11));
			System.out.println(RandomUtils.randomAll(11));

		}
	}
}

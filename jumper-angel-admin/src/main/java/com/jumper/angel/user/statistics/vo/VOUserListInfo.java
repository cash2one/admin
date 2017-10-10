package com.jumper.angel.user.statistics.vo;

import java.io.Serializable;

/**
 * 院区列表数据 VO
 * @author gyx
 * @time 2017年3月15日
 */
public class VOUserListInfo implements Serializable{
	
	private static final long serialVersionUID = 3617911174493971232L;
	//用户id
	private int userId;
	//姓名
	private String userName;
	//电话
	private String mobile;
	//年龄
	private int age;
	//预产期
	private String expectedDateOfConfinement;
	//宝宝生日
	private String babyBirthDay;
	//注册时间
	private String regTime;
	//关联时间
	private String bindingTime;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getExpectedDateOfConfinement() {
		return expectedDateOfConfinement;
	}
	public void setExpectedDateOfConfinement(String expectedDateOfConfinement) {
		this.expectedDateOfConfinement = expectedDateOfConfinement;
	}
	public String getBabyBirthDay() {
		return babyBirthDay;
	}
	public void setBabyBirthDay(String babyBirthDay) {
		this.babyBirthDay = babyBirthDay;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getBindingTime() {
		return bindingTime;
	}
	public void setBindingTime(String bindingTime) {
		this.bindingTime = bindingTime;
	}
	
	
	
	
}

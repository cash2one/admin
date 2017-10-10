package com.jumper.angel.user.statistics.vo;

import java.io.Serializable;

/**
 * 用户详情数据 VO
 * @author gyx
 * @time 2017年3月15日
 */
public class VOUserDetailInfo implements Serializable{
	
	private static final long serialVersionUID = 3617911174493971232L;
	//用户昵称
	private String nickName = "";
	//真实姓名
	private String realName = "";
	//电话
	private String mobile = "";
	//年龄
	private int age;
	//预产期
	private String expectedDateOfConfinement = "";
	//宝宝生日
	private String babyBirthDay = "";
	//省份
	private String proName = "";
	//城市
	private String cityName = "";
	//所属状态
	private int currentIdentity;
	//怀孕状态
	private String pregnantStatus = "";
	//所处孕周
	private String pregnantWeek = "";
	//注册时间
	private String regTime = "";
	//初次关联时间
	private String bindingTime = "";
	//初次关联医院名称
	private String firstBindHospitalName = "";
	//初次关联应用版本
	private String versionName = "";
	//关联设备
	private int mobileType;
	//初次关联操作设备
	private String firstBindMachine = "";
	//当前关联医院
	private String currentBindHospitalName = "";
	//最近一次登录时间
	private String lastLoginTime = "";
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPregnantStatus() {
		return pregnantStatus;
	}
	public void setPregnantStatus(String pregnantStatus) {
		this.pregnantStatus = pregnantStatus;
	}
	public String getPregnantWeek() {
		return pregnantWeek;
	}
	public void setPregnantWeek(String pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
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
	public String getFirstBindHospitalName() {
		return firstBindHospitalName;
	}
	public void setFirstBindHospitalName(String firstBindHospitalName) {
		this.firstBindHospitalName = firstBindHospitalName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getFirstBindMachine() {
		return firstBindMachine;
	}
	public void setFirstBindMachine(String firstBindMachine) {
		this.firstBindMachine = firstBindMachine;
	}
	public String getCurrentBindHospitalName() {
		return currentBindHospitalName;
	}
	public void setCurrentBindHospitalName(String currentBindHospitalName) {
		this.currentBindHospitalName = currentBindHospitalName;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getCurrentIdentity() {
		return currentIdentity;
	}
	public void setCurrentIdentity(int currentIdentity) {
		this.currentIdentity = currentIdentity;
	}
	public int getMobileType() {
		return mobileType;
	}
	public void setMobileType(int mobileType) {
		this.mobileType = mobileType;
	}
	
	
}

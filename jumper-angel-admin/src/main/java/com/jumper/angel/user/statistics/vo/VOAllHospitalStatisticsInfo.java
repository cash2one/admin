package com.jumper.angel.user.statistics.vo;

import java.io.Serializable;

/**
 * 医院总表列表数据 VO
 * @author gyx
 * @time 2017年3月14日
 */
public class VOAllHospitalStatisticsInfo implements Serializable{
	
	//医院id
//	private int hospitalId;
	//医院名称
	private String hospitalName;
	//总用户数
	private int totalCount;
	//日新增用户数
	private int dailyAddCount;
	//医院问诊服务次数
	private int hospConsultantCount;
	//设备租赁服务次数
	private int leaseCount;
	//胎心监护服务次数
	private int heartMonitorCount;
	//体重营养门诊人数
	private int weightOutPatientCount;
	//近7天新增用户数
	private int sevenDaysAddCount;
	//本月新增用户数
	private int currMonthAddCount;
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getDailyAddCount() {
		return dailyAddCount;
	}
	public void setDailyAddCount(int dailyAddCount) {
		this.dailyAddCount = dailyAddCount;
	}
	public int getWeightOutPatientCount() {
		return weightOutPatientCount;
	}
	public void setWeightOutPatientCount(int weightOutPatientCount) {
		this.weightOutPatientCount = weightOutPatientCount;
	}
	public int getHospConsultantCount() {
		return hospConsultantCount;
	}
	public void setHospConsultantCount(int hospConsultantCount) {
		this.hospConsultantCount = hospConsultantCount;
	}
	public int getLeaseCount() {
		return leaseCount;
	}
	public void setLeaseCount(int leaseCount) {
		this.leaseCount = leaseCount;
	}
	public int getHeartMonitorCount() {
		return heartMonitorCount;
	}
	public void setHeartMonitorCount(int heartMonitorCount) {
		this.heartMonitorCount = heartMonitorCount;
	}
	public int getSevenDaysAddCount() {
		return sevenDaysAddCount;
	}
	public void setSevenDaysAddCount(int sevenDaysAddCount) {
		this.sevenDaysAddCount = sevenDaysAddCount;
	}
	public int getCurrMonthAddCount() {
		return currMonthAddCount;
	}
	public void setCurrMonthAddCount(int currMonthAddCount) {
		this.currMonthAddCount = currMonthAddCount;
	}
	
	
	
}

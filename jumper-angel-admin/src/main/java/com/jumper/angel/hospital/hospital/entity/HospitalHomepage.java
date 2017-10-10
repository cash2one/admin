package com.jumper.angel.hospital.hospital.entity;

import java.util.List;

import com.jumper.angel.home.advertise.entity.PromotionAdvertisement;
import com.jumper.angel.utils.ConfigProUtils;

public class HospitalHomepage {
	/** 医院ID **/
	private Integer hospitalId;
	/** 天使医院ID **/
	private Integer angelsoundHopspitalId = Integer.valueOf(ConfigProUtils.get("ANGELSOUND_HOPSPITAL_ID"));
	/** 是否天使医院 **/
	private Boolean isAngelsoundHospital = false;
	/** 当前时间戳 **/
	private Long timestamp;
	/** 广告 **/
	private List<PromotionAdvertisement> banners;
	/**公告*/
	private String notice;
	/**医院服务列表*/
	private List<HospitalServiceList> HospitalServiceList;
	
	
	/**
	 * @return the notice
	 */
	public String getNotice() {
		return notice;
	}
	/**
	 * @param notice the notice to set
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}
	/**
	 * @return the hospitalId
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}
	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * @return the angelsoundHopspitalId
	 */
	public Integer getAngelsoundHopspitalId() {
		return angelsoundHopspitalId;
	}
	/**
	 * @param angelsoundHopspitalId the angelsoundHopspitalId to set
	 */
	public void setAngelsoundHopspitalId(Integer angelsoundHopspitalId) {
		this.angelsoundHopspitalId = angelsoundHopspitalId;
	}
	/**
	 * @return the isAngelsoundHospital
	 */
	public Boolean getIsAngelsoundHospital() {
		return isAngelsoundHospital;
	}
	/**
	 * @param isAngelsoundHospital the isAngelsoundHospital to set
	 */
	public void setIsAngelsoundHospital(Boolean isAngelsoundHospital) {
		this.isAngelsoundHospital = isAngelsoundHospital;
	}
	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the banners
	 */
	public List<PromotionAdvertisement> getBanners() {
		return banners;
	}
	/**
	 * @param banners the banners to set
	 */
	public void setBanners(List<PromotionAdvertisement> banners) {
		this.banners = banners;
	}
	public List<HospitalServiceList> getHospitalServiceList() {
		return HospitalServiceList;
	}
	public void setHospitalServiceList(List<HospitalServiceList> hospitalServiceList) {
		HospitalServiceList = hospitalServiceList;
	}

}

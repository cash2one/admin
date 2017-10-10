package com.jumper.angel.home.advertise.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.jumper.angel.utils.ConfigProUtils;
/**
 * 广告实体 entity
 * @author gyx
 * @time 2017年2月7日
 */
public class PromotionAdvertisement implements Serializable {
	
	private static final long serialVersionUID = -8417400826980425678L;
	
	private Integer id;
	//广告描述
	private String description;
	//图片地址
	private String imageUrl;
	//广告的URL地址
	private String webUrl;
	//添加时间
	private Timestamp addTime;
	//添加时间字符串
	private String addTimeStr;
	//是否推送为广告：0.没有;1.推送
	private Integer isPush;
	//是否为医生推送为广告
	private Integer docPush;
	//是否为活动链接
	private Integer isActivity;
	//医院id
	private Integer hospitalId;
	//是否为医院banners中的最后一个(由我们设置 0：是  1：否)
	private Integer isBanner;
	//文件前缀
	private String filePrefix = ConfigProUtils.get("file_path");

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getIsPush() {
		return this.isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	public Integer getDocPush() {
		return this.docPush;
	}

	public void setDocPush(Integer docPush) {
		this.docPush = docPush;
	}

	public Integer getIsActivity() {
		return this.isActivity;
	}

	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}

	public Integer getIsBanner() {
		return isBanner;
	}

	public void setIsBanner(Integer isBanner) {
		this.isBanner = isBanner;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	
	
}
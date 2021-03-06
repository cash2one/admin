package com.jumper.angel.home.health.entity;

import java.io.Serializable;
import com.jumper.angel.utils.ConfigProUtils;

/**
 * 孕期信息entity
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-11-30
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class PregnancyInfo implements Serializable {
    
	private static final long serialVersionUID = -2703212412444085694L;
	//孕周
    private Integer week;
    //身长
    private String fetalHeight;
    //顶臀长
    private String bothArm;
    //胎重
    private String fetalWeight;
    //双顶颈
    private String bothNeck;
    //孕期描述
    private String weekDescription;
    //图片
    private String imageUrl;
    //孕期状态
    private int pregnancyStatus;
    //是否修改图片  0:修改信息  1:修改图片
    private int updateImage;
    //图片地址前缀
    private String imagePrefix = ConfigProUtils.get("file_path");

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getFetalHeight() {
        return fetalHeight;
    }

    public void setFetalHeight(String fetalHeight) {
        this.fetalHeight = fetalHeight;
    }

    public String getBothArm() {
        return bothArm;
    }

    public void setBothArm(String bothArm) {
        this.bothArm = bothArm;
    }

    public String getFetalWeight() {
        return fetalWeight;
    }

    public void setFetalWeight(String fetalWeight) {
        this.fetalWeight = fetalWeight;
    }

    public String getBothNeck() {
        return bothNeck;
    }

    public void setBothNeck(String bothNeck) {
        this.bothNeck = bothNeck == null ? null : bothNeck.trim();
    }

    public String getWeekDescription() {
        return weekDescription;
    }

    public void setWeekDescription(String weekDescription) {
        this.weekDescription = weekDescription == null ? null : weekDescription.trim();
    }

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPregnancyStatus() {
		return pregnancyStatus;
	}

	public void setPregnancyStatus(int pregnancyStatus) {
		this.pregnancyStatus = pregnancyStatus;
	}

	public int getUpdateImage() {
		return updateImage;
	}

	public void setUpdateImage(int updateImage) {
		this.updateImage = updateImage;
	}

	public String getImagePrefix() {
		return imagePrefix;
	}

	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
}
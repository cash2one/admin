package com.jumper.angel.hospital.hospital.entity;

import java.util.Date;
/**
 * 医生科室
*
* HospitalDoctorMajor
*jumper-angel-admin
* 2017年6月26日 下午4:23:15
*
* @version 1.0.0
 */
public class HospitalDoctorMajor {
    private Integer id;
    /**科室名称**/
    private String major;
    /**科室图片**/
    private String imageUrl;
    /**文件路径**/
    private String filePath;
    /**添加时间**/
    private Date addTime;
    /**来源（1：弘扬）**/
    private Integer source;

    public Integer getId() {
        return id;
    }

    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
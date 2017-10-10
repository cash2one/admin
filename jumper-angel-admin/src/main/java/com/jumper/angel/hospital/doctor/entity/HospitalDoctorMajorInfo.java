package com.jumper.angel.hospital.doctor.entity;

import java.util.Date;
/**
 *	医生科室信息
*
* HospitalDoctorMajorBean
*jumper-doctor
* 2016年12月22日 下午4:16:04
*
* @version 1.0.0
 */
public class HospitalDoctorMajorInfo  implements java.io.Serializable{
	/**serialVersionUID:TODO（用一句话描述这个变量表示什么）*/
	private static final long serialVersionUID = -1764809901679302471L;
	private Integer id;
	/**医学专业内容，目前暂时有:1.产科;2：乳腺科 ;3:妇科;4;营养科5：全科*/
	private String major;
	/**医学专业对应的图片URL地址*/
    private String imageUrl;
    /**添加时间*/
    private Date addTime;
    /**来源*/
    private Integer source;

    
    public Integer getId() {
		return id;
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
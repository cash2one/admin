package com.jumper.angel.hospital.doctor.entity;

import java.util.Date;
/**
 * 医生信息
*
* HospitalDoctorInfoBean
*Demo
* 2016年12月22日 下午4:36:28
* @version 1.0.0
 */
public class HospitalDoctorInfo  implements java.io.Serializable{
    /** serialVersionUID:TODO（用一句话描述这个变量表示什么）	*/
	private static final long serialVersionUID = -6659650032106373264L;
	private Integer id;
	/** 医院ID	*/
    private Integer hospitalId;
    /** 医生名字*/
    private String name;
    /** 医生介绍*/
    private String introduction;
    /** 医生图片*/
    private String imgUrl;
    /** 添加时间*/
    private Date addTime;
    /** 粉丝数*/
    private Integer fansNumber;
    /** 角色类型,1:管理员,0:普通角色*/
    private Integer type;
    /** 密码*/
    private String password;
    /** 医生擅长*/
    private String expert;
    /**医生职称对应的ID号*/
    private Integer titleId;
    /** 联系电话*/
    private String phone;
    /** 医生学习专业ID号*/
    private Integer majorId;
    /** 医生id*/
    private Integer doctorId;
    /** 标识医生的二维码图片*/
    private String qrCodeUrl;
    /** 医生的资职认证的图片URL*/
    private String certificationUrl;
    /** 医生的执业认证的图片URL*/
    private String physicianPraticeLicenseUrl;
    /**医生的状态,0：待认证 ； 1：认证通过；-1：认证失败；2：未认证*/
    private Integer status;
    /** 医生职称*/
    private String title;
    /** 医生所在科室电话*/
    private String majorPhone;
    /** 学术成就*/
    private String achievement;
    /** 教育背景*/
    private String education;
    /**审核时间 */
    private Date applyDate;
    /**审核次数 */
    private Integer applyTimes;
    /** 审核通过时间*/
    private Date passTime;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert == null ? null : expert.trim();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl == null ? null : qrCodeUrl.trim();
    }

    public String getCertificationUrl() {
        return certificationUrl;
    }

    public void setCertificationUrl(String certificationUrl) {
        this.certificationUrl = certificationUrl == null ? null : certificationUrl.trim();
    }

    public String getPhysicianPraticeLicenseUrl() {
        return physicianPraticeLicenseUrl;
    }

    public void setPhysicianPraticeLicenseUrl(String physicianPraticeLicenseUrl) {
        this.physicianPraticeLicenseUrl = physicianPraticeLicenseUrl == null ? null : physicianPraticeLicenseUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMajorPhone() {
        return majorPhone;
    }

    public void setMajorPhone(String majorPhone) {
        this.majorPhone = majorPhone == null ? null : majorPhone.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplyTimes() {
        return applyTimes;
    }

    public void setApplyTimes(Integer applyTimes) {
        this.applyTimes = applyTimes;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }
}
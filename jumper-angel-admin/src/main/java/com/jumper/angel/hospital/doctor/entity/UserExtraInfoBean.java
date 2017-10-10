package com.jumper.angel.hospital.doctor.entity;

import java.util.Date;
/**用户额外的信息对象*/
public class UserExtraInfoBean {
	private Integer id;
	/**用户ID*/
    private Integer userId;
    /**登陆ip*/
    private String loginIp;
    /**登陆时间*/
    private Date loginTime;
    /**登录手机类型,0:android,1:IOS*/
    private Integer mobileType;
    /**年龄*/
    private Integer age;
    /**身份证*/
    private String identification;
    /**真实姓名*/
    private String realName;
    /**联系方式*/
    private String contactPhone;
    /**身高*/
    private String height;
    /**体重*/
    private Double weight;
    /**宝宝生日*/
    private Date babyBirthday;
    /**宝宝行吧*/
    private Byte babySex;
    /**是否是普通医院*/
    private Integer commonHospital;
    /**0表示怀孕，1表示已有宝宝*/
    private Byte currentIdentity;
    /**最后一次周期*/
    private Date lastPeriod;
    /**周期时间（周）*/
    private Integer periodCycle;
    /**周期时间（天）*/
    private Integer periodDay;
    /**是否是中国用户*/
    private Integer isChinaUser;
    /**口服葡萄糖耐量试验:ogtt*/
    private String ogtt;
    /**糖化血红蛋白*/
    private Double hba1c;
    /**空腹血糖*/
    private Double glu;
    /**血压*/
    private String bp;
    /**血脂四项,总胆固醇（TC）、甘油三酯（TG）、高密度脂蛋白胆固醇（HDL-C）、低密度脂蛋白胆固醇（LDL-C）*/
    private String bloodFat;
    /**绑定保健号医院ID*/
    private Integer baojianhaoHospitalId;
    /**档案结案状态*/
    private Integer jieanState;
    /**出生日期*/
    private Date birthday;
    /**是否参加体重管理0：未参加 1：已参加*/
    private Integer checkStatus;
    /**绑定医院名称*/
    private String bindhospitalname;
    /**身份证图像*/
    private String idenIcon;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification == null ? null : identification.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(Date babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public Byte getBabySex() {
        return babySex;
    }

    public void setBabySex(Byte babySex) {
        this.babySex = babySex;
    }

    public Integer getCommonHospital() {
        return commonHospital;
    }

    public void setCommonHospital(Integer commonHospital) {
        this.commonHospital = commonHospital;
    }

    public Byte getCurrentIdentity() {
        return currentIdentity;
    }

    public void setCurrentIdentity(Byte currentIdentity) {
        this.currentIdentity = currentIdentity;
    }

    public Date getLastPeriod() {
        return lastPeriod;
    }

    public void setLastPeriod(Date lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    public Integer getPeriodCycle() {
        return periodCycle;
    }

    public void setPeriodCycle(Integer periodCycle) {
        this.periodCycle = periodCycle;
    }

    public Integer getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(Integer periodDay) {
        this.periodDay = periodDay;
    }

    public Integer getIsChinaUser() {
        return isChinaUser;
    }

    public void setIsChinaUser(Integer isChinaUser) {
        this.isChinaUser = isChinaUser;
    }

    public String getOgtt() {
        return ogtt;
    }

    public void setOgtt(String ogtt) {
        this.ogtt = ogtt == null ? null : ogtt.trim();
    }

    public Double getHba1c() {
        return hba1c;
    }

    public void setHba1c(Double hba1c) {
        this.hba1c = hba1c;
    }

    public Double getGlu() {
        return glu;
    }

    public void setGlu(Double glu) {
        this.glu = glu;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp == null ? null : bp.trim();
    }

    public String getBloodFat() {
        return bloodFat;
    }

    public void setBloodFat(String bloodFat) {
        this.bloodFat = bloodFat == null ? null : bloodFat.trim();
    }

    public Integer getBaojianhaoHospitalId() {
        return baojianhaoHospitalId;
    }

    public void setBaojianhaoHospitalId(Integer baojianhaoHospitalId) {
        this.baojianhaoHospitalId = baojianhaoHospitalId;
    }

    public Integer getJieanState() {
        return jieanState;
    }

    public void setJieanState(Integer jieanState) {
        this.jieanState = jieanState;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getBindhospitalname() {
        return bindhospitalname;
    }

    public void setBindhospitalname(String bindhospitalname) {
        this.bindhospitalname = bindhospitalname == null ? null : bindhospitalname.trim();
    }

    public String getIdenIcon() {
        return idenIcon;
    }

    public void setIdenIcon(String idenIcon) {
        this.idenIcon = idenIcon == null ? null : idenIcon.trim();
    }
}
package com.jumper.angel.sociality.group.model.vo;

import java.util.Date;

public class UserinfoVo  implements java.io.Serializable {
	/**类的版本号*/
	private static final long serialVersionUID = 1979006802200599L;
	/** ID号 */
	private Integer id;
	/** 手机号码 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 昵称 */
	private String nickName;
	/** 国家 */
	private Integer country;
	/** 省份ID */
	private Integer province;
	/** 城市ID */
	private Integer city;
	/** 用户头像URL */
	private String userImg;
	/** 预产期 */
	private Date expectedDateOfConfinement;
	/** 用户状态 0：禁用 1：激活 */
	private Integer status;
	/** 注册时间 */
	private Date regTime;
	/** 用户密码 */
	private String password;
	/** 是否开启推送消息,0:关闭,1:开启 */
	private Integer isSwitchPushMsg;
	/** 第三方登录的ID号 */
	private String openId;
	/** 登录的用户类型,0:本地,1:新浪,2:QQ,3:微信 */
	private Integer userType;
	/** 用户金币 */
	private Integer gold;
	/** 孕期阶段(0-6,孕早期，孕中期…1-3岁) */
	private Integer pregnantStage;
	/** 用户孕期及宝宝大小周数 */
	private Integer pregnantWeek;
	/** 新浪第三方ID */
	private String sinaOpenId;
	/** QQ第三方ID */
	private String qqOpenId;
	/** 微信第三方ID */
	private String weixinOpenId;
	/** 就诊卡号 */
	private String medicalNum;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg == null ? null : userImg.trim();
	}

	public Date getExpectedDateOfConfinement() {
		return expectedDateOfConfinement;
	}

	public void setExpectedDateOfConfinement(Date expectedDateOfConfinement) {
		this.expectedDateOfConfinement = expectedDateOfConfinement;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getIsSwitchPushMsg() {
		return isSwitchPushMsg;
	}

	public void setIsSwitchPushMsg(Integer isSwitchPushMsg) {
		this.isSwitchPushMsg = isSwitchPushMsg;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getPregnantStage() {
		return pregnantStage;
	}

	public void setPregnantStage(Integer pregnantStage) {
		this.pregnantStage = pregnantStage;
	}

	public Integer getPregnantWeek() {
		return pregnantWeek;
	}

	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}

	public String getSinaOpenId() {
		return sinaOpenId;
	}

	public void setSinaOpenId(String sinaOpenId) {
		this.sinaOpenId = sinaOpenId == null ? null : sinaOpenId.trim();
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId == null ? null : qqOpenId.trim();
	}

	public String getWeixinOpenId() {
		return weixinOpenId;
	}

	public void setWeixinOpenId(String weixinOpenId) {
		this.weixinOpenId = weixinOpenId == null ? null : weixinOpenId.trim();
	}

	public String getMedicalNum() {
		return medicalNum;
	}

	public void setMedicalNum(String medicalNum) {
		this.medicalNum = medicalNum == null ? null : medicalNum.trim();
	}


}

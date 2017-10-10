package com.jumper.angel.hospital.hospital.vo;

public class HospitalInfoVo {
	/**
	 * 医院ID
	 */
	private Integer id;
	/**
	 * 医院名称
	 */
	private String name;
	/**
	 * 医院图片
	 */
	private String imgUrl;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 省id
	 */
	private Integer provinceId;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 市
	 */
	private Integer cityId;
	/**
	 * 网络医院账号
	 */
	private String userName;
	
	/**
	 * 已开通服务
	 */
	private String[] service;
	/**
	 * 添加时间
	 */
	private String addTime;
	/**
	 * 开通时间
	 */
	private String loginTime;
	/**
	 * 是否锁定：0未锁，1锁定
	 */
	private Integer isLocked;
	/**
	 * 账户ID
	 */
	private Integer monitorId;
	/**文件路径**/
    private String filePath;
    /**是否开通线上支付0.未开通，1已开通**/
    private Integer isPayment;
    /**是否禁用1.正常，0禁用**/
    private Integer isEnabled;
	
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Integer getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Integer monitorId) {
		this.monitorId = monitorId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String[] getService() {
		return service;
	}
	public void setService(String[] service) {
		this.service = service;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	
}

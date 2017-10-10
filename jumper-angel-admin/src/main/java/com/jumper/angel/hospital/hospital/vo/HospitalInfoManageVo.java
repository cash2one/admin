package com.jumper.angel.hospital.hospital.vo;

public class HospitalInfoManageVo {
	/**
	 * 医院ID
	 */
	private Integer id;

	/**
	 * 已有科室
	 */
	private String[] majorids;
	/**
	 * 类型,如add,edit...
	 */
	private String type;

	private Integer editType;
	/**
	 * 医院名称
	 */
	private String name;
	/**
	 * 医院简介
	 */
	private String introduction;
	/**
	 * 医院图片原始名称
	 */
	private String file;
	/**
	 * 医院图片
	 */
	private String uploadfile;
	/**
	 * 医院地址
	 */
	private String address;
	/**
	 * 省
	 */
	private Integer province;
	/**
	 * 市
	 */
	private Integer city;
	/**
	 * 新增/修改的科室
	 */
	private Integer[] majorId;
	/**
	 * 关联类型
	 */
	private Integer typeChange;
	/**
	 * 关联医院
	 */
	private Integer hospital;

	private Integer page;

	private Integer statusType;

	private Integer serviceType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String[] getMajorids() {
		return majorids;
	}

	public void setMajorids(String[] majorids) {
		this.majorids = majorids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEditType() {
		return editType;
	}

	public void setEditType(Integer editType) {
		this.editType = editType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer[] getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer[] majorId) {
		this.majorId = majorId;
	}

	public Integer getTypeChange() {
		return typeChange;
	}

	public void setTypeChange(Integer typeChange) {
		this.typeChange = typeChange;
	}

	public Integer getHospital() {
		return hospital;
	}

	public void setHospital(Integer hospital) {
		this.hospital = hospital;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	
}

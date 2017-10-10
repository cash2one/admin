package com.jumper.angel.sociality.group.model.po;

import com.jumper.angel.utils.ConfigProUtils;

public class SocialityGroupPo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1978092123654144L;
	/** ID */
	private java.lang.Integer id;
	/** 交流圈ID */
	private java.lang.String groupId;
	/** 交流圈名称 */
	private java.lang.String groupName;
	/** 交流圈简介 */			 
	private java.lang.String groupBriefIntr;
	/** 交流圈二维码URL */
	private java.lang.String groupImgUrl;
	/** 交流圈成员数 */
	private java.lang.Integer peopleNumber;
	/** 是否顶置（1：设置顶置，2：取消顶置） */
	private java.lang.Integer overhead;
	/** 数据状态(1:：正常 2：禁用) */
	private java.lang.Integer dataStatus;
	/** 创建人 */
	private java.lang.String createUserId;
	/** 创建时间 */
	private java.lang.Long createUserTime;
	/** 设为非公开圈子，勾选则APP端不能搜到，只能扫码加入。1：是 0：否 */
	private java.lang.Integer allowAdd;
	/** 圈子封面 */
	private String  coverUrl;
	
	//图片地址前缀
    private String imagePrefix = ConfigProUtils.get("file_path");
    
	public SocialityGroupPo() {
		super();
	}

	public SocialityGroupPo(Integer id, String groupId, String groupName,
			String groupBriefIntr, String groupImgUrl, Integer peopleNumber,
			Integer overhead, Integer dataStatus, String createUserId,
			Long createUserTime, Integer allowAdd) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupBriefIntr = groupBriefIntr;
		this.groupImgUrl = groupImgUrl;
		this.peopleNumber = peopleNumber;
		this.overhead = overhead;
		this.dataStatus = dataStatus;
		this.createUserId = createUserId;
		this.createUserTime = createUserTime;
		this.allowAdd = allowAdd;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getGroupId() {
		return groupId;
	}

	public void setGroupId(java.lang.String groupId) {
		this.groupId = groupId;
	}

	public java.lang.String getGroupName() {
		return groupName;
	}

	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	public java.lang.String getGroupBriefIntr() {
		return groupBriefIntr;
	}

	public void setGroupBriefIntr(java.lang.String groupBriefIntr) {
		this.groupBriefIntr = groupBriefIntr;
	}

	public java.lang.String getGroupImgUrl() {
		return groupImgUrl;
	}

	public void setGroupImgUrl(java.lang.String groupImgUrl) {
		this.groupImgUrl = groupImgUrl;
	}

	public java.lang.Integer getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(java.lang.Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public java.lang.Integer getOverhead() {
		return overhead;
	}

	public void setOverhead(java.lang.Integer overhead) {
		this.overhead = overhead;
	}

	public java.lang.Integer getDataStatus() {
		
		
		return dataStatus;
	}

	public void setDataStatus(java.lang.Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public java.lang.String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	public java.lang.Long getCreateUserTime() {
		return createUserTime;
	}

	public void setCreateUserTime(java.lang.Long createUserTime) {
		this.createUserTime = createUserTime;
	}

	public java.lang.Integer getAllowAdd() {
		return allowAdd;
	}

	public void setAllowAdd(java.lang.Integer allowAdd) {
		this.allowAdd = allowAdd;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getImagePrefix() {
		return imagePrefix;
	}

	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
}
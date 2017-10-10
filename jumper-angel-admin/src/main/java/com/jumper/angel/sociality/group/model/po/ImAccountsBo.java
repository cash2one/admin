package com.jumper.angel.sociality.group.model.po;


public class ImAccountsBo implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 2043395967764480L;

	/** 表ID */
	private java.lang.Long id;
	/** 用户Id */
	private java.lang.String userId;
	/** im帐号 */
	private java.lang.String chatId;
	/** 第三方系统用户标识 */
	private java.lang.String openid;
	/** 系统标识 */
	private java.lang.String appid;
	/** 创建时间 */
	private java.lang.Long createTime;
	/** 是否禁用 1：正常 1：禁用 */
	private java.lang.Long isDisable;
	/** 扩展字段 */
	private java.lang.String extend;
	/** 扩展字段2 */
	private java.lang.String extend1;

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getChatId() {
		return this.chatId;
	}

	public void setChatId(java.lang.String chatId) {
		this.chatId = chatId;
	}

	public java.lang.String getOpenid() {
		return this.openid;
	}

	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	public java.lang.String getAppid() {
		return appid;
	}

	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}

	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	public java.lang.Long getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(java.lang.Long isDisable) {
		this.isDisable = isDisable;
	}

	public java.lang.String getExtend() {
		return this.extend;
	}

	public void setExtend(java.lang.String extend) {
		this.extend = extend;
	}

	public java.lang.String getExtend1() {
		return this.extend1;
	}

	public void setExtend1(java.lang.String extend1) {
		this.extend1 = extend1;
	}

	public String toString() {
		return "[" + "id:" + getId() + "," + "chatId:" + getChatId() + ","
				+ "openid:" + getOpenid() + "," + "createTime:"
				+ getCreateTime() + "," + "isDisable:" + getIsDisable() + ","
				+ "extend:" + getExtend() + "," + "extend1:" + getExtend1()
				+ "]";
	}
}
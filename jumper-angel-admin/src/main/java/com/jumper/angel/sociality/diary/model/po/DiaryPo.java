package com.jumper.angel.sociality.diary.model.po;

/**
 * 宝妈日记实体类
 * 
 * @ClassName: DiaryPo
 * @author huangzg 2017年1月14日 下午1:51:39
 */
public class DiaryPo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 2008989486450688L;
	/** 表ID */
	private java.lang.Long id;
	/** 日记内容 */
	private java.lang.String content;
	/** 日记图片URL */
	private java.lang.String imgUrl;
	/** 添加时间 */
	private java.util.Date addTime;
	/** 是否删除(1：是 0：否） */
	private java.lang.Integer isDelete;
	/** 创建人userId */
	private java.lang.Integer userId;
	/** 创建人用户名 */
	private java.lang.String userName;
	/** 0:表示没有填写；1：星星眼；2：开心；3：囧；4：不开心；5：生气 */
	private java.lang.Integer moodExpression;
	/** 孕周id(对应pregnant_week表) */
	private java.lang.Integer pregnantWeekId;

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(java.lang.String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public java.util.Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}

	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(java.lang.Integer isDelete) {
		this.isDelete = isDelete;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.Integer getMoodExpression() {
		return this.moodExpression;
	}

	public void setMoodExpression(java.lang.Integer moodExpression) {
		this.moodExpression = moodExpression;
	}

	public java.lang.Integer getPregnantWeekId() {
		return this.pregnantWeekId;
	}

	public void setPregnantWeekId(java.lang.Integer pregnantWeekId) {
		this.pregnantWeekId = pregnantWeekId;
	}

	public String toString() {
		return "[" + "id:" + getId() + "," + "content:" + getContent() + ","
				+ "imgUrl:" + getImgUrl() + "," + "addTime:" + getAddTime()
				+ "," + "isDelete:" + getIsDelete() + "," + "userId:"
				+ getUserId() + "," + "moodExpression:" + getMoodExpression()
				+ "," + "pregnantWeekId:" + getPregnantWeekId() + "]";
	}
}
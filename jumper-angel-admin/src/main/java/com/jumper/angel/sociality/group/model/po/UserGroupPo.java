package com.jumper.angel.sociality.group.model.po;

/**
 * 社交 交流圈成员 实体类
 * 
 * @ClassName: UserGroupPo
 * @author huangzg 2016年12月24日 上午10:03:00
 */
public class UserGroupPo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1979006802200576L;
	
	private java.lang.Integer id;
	/** ChatId（聊天ID） */
	private java.lang.String userId;
	/** 交流圈ID */
	private java.lang.String groupId;
	/** 创建时间 */
	private java.lang.Long createTime;
	/** 最后发言时间 */
	private java.lang.Long speakingTime;
	/** 是否禁言（1：是，0：否） */
	private java.lang.Long isBlacklist;
	/** 是否删除（1：是，0：否） */
	private java.lang.Long isDelete;
	/** 是否管理员（1：是，0：否） */
	private java.lang.Long isKeeper;
	
	private Long debatepostTime;
	
	private Integer notify;
	
	/**设置为医生和管理员的时候的更新时间，用于APP排序*/
	private java.lang.Long updateTime;
	
	public Long getDebatepostTime() {
		return debatepostTime;
	}

	public void setDebatepostTime(Long debatepostTime) {
		this.debatepostTime = debatepostTime;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getGroupId() {
		return groupId;
	}

	public void setGroupId(java.lang.String groupId) {
		this.groupId = groupId;
	}

	public java.lang.Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	public java.lang.Long getSpeakingTime() {
		return speakingTime;
	}

	public void setSpeakingTime(java.lang.Long speakingTime) {
		this.speakingTime = speakingTime;
	}
	public java.lang.Long getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(java.lang.Long isBlacklist) {
		this.isBlacklist = isBlacklist;
	}

	public java.lang.Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(java.lang.Long isDelete) {
		this.isDelete = isDelete;
	}

	public java.lang.Long getIsKeeper() {
		return isKeeper;
	}

	public void setIsKeeper(java.lang.Long isKeeper) {
		this.isKeeper = isKeeper;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public Integer getNotify() {
		return notify;
	}

	public void setNotify(Integer notify) {
		this.notify = notify;
	}

	public java.lang.Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}

}
/** 
 * Project Name:jumper-angel-manage 
 * File Name:OperationRecordPO.java 
 * Package Name:com.jumper.angel.sociality.operationrecord.model 
 * Date:2017年1月11日下午4:38:45 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.operationrecord.model.po;

public class OperationRecordPO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -24049816106207007L;
	/** 记录ID 自增 */
	private Long recordId;
	/** 记录操作内容 */
	private String recordContent;
	/** 操作类型 */
	private Integer recordType;
	/** 记录时间 */
	private Long recordTime;
	/** 操作记录人ID */
	private String recordUserId;
	/** 操作记录人名称 */
	private String recordUserName;
	/**
	 * 帖子ID
	 */
	private Long debatepostId;
	/**
	 * 话题ID
	 */
    private Long topicId;
    /**
	 * 评论ID
	 */
    private Long  commentId;
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getRecordContent() {
		return recordContent;
	}
	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Long getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Long recordTime) {
		this.recordTime = recordTime;
	}
	public String getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
	public String getRecordUserName() {
		return recordUserName;
	}
	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}
	public Long getDebatepostId() {
		return debatepostId;
	}
	public void setDebatepostId(Long debatepostId) {
		this.debatepostId = debatepostId;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
}

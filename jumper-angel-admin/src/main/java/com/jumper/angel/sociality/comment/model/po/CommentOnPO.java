/** 
 * Project Name:jumper-angel-manage 
 * File Name:CommentOnPO.java 
 * Package Name:com.jumper.angel.sociality.comment.model.po 
 * Date:2017年1月9日上午11:38:44 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.model.po;
import java.sql.Timestamp;

public class CommentOnPO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2087707629695444603L;
	/**
	 * 主键ID
	 */
	private Long  id;
	/**
	 * 回复的评论ID，评论FID为0
	 */
	private Long fid;
	/**
	 * 帖子ID
	 */
	private Long debatepostId;
	/**
	 * 话题ID
	 */
	private Long topicId;
	/**
	 * 评论用户id
	 */
	private String fromUserId;
	/**
	 * 被评论者ID
	 */
	private String toUserId;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 消息内型 评论:comment 回复 docomment
	 */
	private String messageType;
	/**
	 * 1 正常 2 隐藏 3 删除
	 */
	private Integer status;
	/**
	 * 层级 暂时用不到
	 */
	private Integer grade;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getDebatepostId() {
		return debatepostId;
	}
	public void setDebatepostId(Long debatepostId) {
		this.debatepostId = debatepostId;
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
}

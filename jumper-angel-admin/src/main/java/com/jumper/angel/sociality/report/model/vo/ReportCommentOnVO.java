/** 
 * 被举报的评论
 * Project Name:jumper-angel-manage 
 * File Name:ReportCommentVO.java 
 * Package Name:com.jumper.angel.sociality.report.model.vo 
 * Date:2017年1月10日上午9:41:20 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.report.model.vo;

public class ReportCommentOnVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -116850983928823487L;
	/**
	 * 评论ID
	 */
	private Long id;
	/**
	 *  评论父ID，回复的评论ID
	 */
	private Long fid;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * comment:评论  docomment:回复
	 */
	private String messageType;
	/**
	 * 帖子ID
	 */
	private Long debatepostId;
	/**
	 * 1 正常 2 隐藏 3删除
	 */
	private Integer status;
	/**
	 * 帖子标题
	 */
	private String debatepostTitle;
	/**
	 * 评论人昵称
	 */
	private String fromNickName;
	/**
	 * 被评论人昵称
	 */
	private String toNickName;
	/**
	 * 举报次数
	 */
	private Integer reportNum;
	//查询条件
	/**
	 * 查询状态
	 */
	private Integer dataStatus;
	/**
	 * 模糊查询 话题标题或发帖人
	 */
	private String keyword;
	 /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
	
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
	public Long getDebatepostId() {
		return debatepostId;
	}
	public void setDebatepostId(Long debatepostId) {
		this.debatepostId = debatepostId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDebatepostTitle() {
		return debatepostTitle;
	}
	public void setDebatepostTitle(String debatepostTitle) {
		this.debatepostTitle = debatepostTitle;
	}
	public String getFromNickName() {
		return fromNickName;
	}
	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}
	public String getToNickName() {
		return toNickName;
	}
	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}
	public Integer getReportNum() {
		return reportNum;
	}
	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}
	public Integer getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}
	public Integer getEveryPage() {
		return everyPage;
	}
	public void setEveryPage(Integer everyPage) {
		this.everyPage = everyPage;
	}
}

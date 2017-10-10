/** 
 * 举报的帖子对象
 * Project Name:jumper-angel-manage 
 * File Name:ReportDebatepostVO.java 
 * Package Name:com.jumper.angel.sociality.report.model.vo 
 * Date:2017年1月9日下午4:31:40 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.report.model.vo;

public class ReportDebatepostVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4196245793405017403L;
	/**
	 * 帖子ID
	 */
	private Long debatepostId;
	/**
	 * 话题ID
	 */
	private Long topicId;
	/**
	 * 帖子标题
	 */
	private String debatepostTitle;
	/**
	 * 话题名称
	 */
	private String topicName;
	/**
	 * 帖子的数据状态
	 */
	private Integer isDelete;
	/**
	 * 用户昵称
	 */
	private String userNickName;
	/**
	 * 举报次数
	 */
	private Integer reportNum;
	//查询条件
	/**
	 * 查询状态
	 */
	private Integer status;
	/**
	 * 模糊查询 话题标题或发帖人
	 */
	private String keyword;
	//分页
	 /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    
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
	public String getDebatepostTitle() {
		return debatepostTitle;
	}
	public void setDebatepostTitle(String debatepostTitle) {
		this.debatepostTitle = debatepostTitle;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public Integer getReportNum() {
		return reportNum;
	}
	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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

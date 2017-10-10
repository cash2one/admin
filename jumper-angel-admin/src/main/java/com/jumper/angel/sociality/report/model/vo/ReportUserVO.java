/** 
 * Project Name:jumper-angel-manage 
 * File Name:ReportUserVO.java 
 * Package Name:com.jumper.angel.sociality.report.model.vo 
 * Date:2017年1月10日下午4:09:52 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.report.model.vo;

public class ReportUserVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1427125422501336018L;
	/**
	 * 发送人ID
	 */
	private String userId;
	/**
	 * 交流圈ID
	 */
	private String groupId;
	/**
	 * 交流圈名称
	 */
	private String groupName;
	/**
	 * 用户昵称
	 */
	private String userNickName;
	/**
	 * 被举报的内容
	 */
	private String beReportMessage;
	/**
	 * 被举报的次数
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
	 /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getBeReportMessage() {
		return beReportMessage;
	}
	public void setBeReportMessage(String beReportMessage) {
		this.beReportMessage = beReportMessage;
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

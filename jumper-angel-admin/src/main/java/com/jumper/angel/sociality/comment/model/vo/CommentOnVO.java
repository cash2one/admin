/** 
 * Project Name:jumper-angel-manage 
 * File Name:CommentOnVO.java 
 * Package Name:com.jumper.angel.sociality.comment.model.po 
 * Date:2017年1月9日上午11:40:55 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.model.vo;

import com.jumper.angel.sociality.comment.model.po.CommentOnPO;

public class CommentOnVO extends CommentOnPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5658190080867747799L;
	
	//用户
    private String fromNickName;
    private String fromUserImg;
    private String toNickName;
    private String toUserImg;
    //帖子标题
    private String debatepostTitle;
    //格式化时间
    private String strDateTime;
    //删除时间
    private String deletedTime;
    //搜索
    private String keyword;
    /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    //删除人员
    private String deleteAdmin;
	public String getFromNickName() {
		return fromNickName;
	}
	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}
	public String getFromUserImg() {
		return fromUserImg;
	}
	public void setFromUserImg(String fromUserImg) {
		this.fromUserImg = fromUserImg;
	}
	public String getToNickName() {
		return toNickName;
	}
	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}
	public String getToUserImg() {
		return toUserImg;
	}
	public void setToUserImg(String toUserImg) {
		this.toUserImg = toUserImg;
	}
	public String getDebatepostTitle() {
		return debatepostTitle;
	}
	public void setDebatepostTitle(String debatepostTitle) {
		this.debatepostTitle = debatepostTitle;
	}
	public String getStrDateTime() {
		return strDateTime;
	}
	public void setStrDateTime(String strDateTime) {
		this.strDateTime = strDateTime;
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
	public String getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(String deletedTime) {
		this.deletedTime = deletedTime;
	}
	public String getDeleteAdmin() {
		return deleteAdmin;
	}
	public void setDeleteAdmin(String deleteAdmin) {
		this.deleteAdmin = deleteAdmin;
	}
}

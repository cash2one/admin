package com.jumper.angel.sociality.topic.model.vo;

import com.jumper.angel.sociality.topic.model.po.TopicPO;
import com.jumper.angel.utils.ConfigProUtils;

public class TopicVO extends TopicPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7579843268070010020L;
	
    /**
     * 格式化后的日期格式
     */
    private String createDateTime;
    /**
     *创建话题组人的昵称 
     */
    private String userName;
    /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    /**
     * 话题状态
     */
    private String status;
    
    //图片地址前缀
    private String imagePrefix = ConfigProUtils.get("file_path");
    /**
     * 话题名称的拼音
     */
    private String pingYin;
	
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
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
	
	public String getStatus() {
		int isDelete = this.getIsDelete();
		int isDataState = this.getDataState();
		if(isDelete == 0 && isDataState ==0){
			status = "正常";
		}
		if(isDelete == 0 && isDataState ==1){
			status = "正常";
		}
		if((isDelete == 1 && isDataState ==0) || (isDelete == 1 && isDataState ==1)){
			status = "禁用中";
		}
		if((isDelete == 2 && isDataState ==0) || (isDelete == 2 && isDataState ==1)){
			status = "禁用中";
		}
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getImagePrefix() {
		return imagePrefix;
	}
	
	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
	public String getPingYin() {
		return pingYin;
	}
	public void setPingYin(String pingYin) {
		this.pingYin = pingYin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
package com.jumper.angel.sociality.group.model.vo;

import com.jumper.angel.sociality.group.model.po.UserGroupPo;

public class UserGroupVo extends UserGroupPo implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1979006802200578L;

/*	*//** 页码 *//*
	private Integer page;
	*//** 每页条数 *//*
	private Integer rows;*/
    /**
     * 格式化后的日期格式
     */
    private String createDateTime;
    /**
     * 最后一次发帖时间
     */
    private String speakingDateTime;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String mobile;
    
    /**
     * 聊天账号
     */
    private String chatId;
    
/*	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}*/	
    /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    
    //关联user_extra_info表的身份信息
    private Integer currentIdentity;
    
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(Integer everyPage) {
		this.everyPage = everyPage;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getSpeakingDateTime() {
		return speakingDateTime;
	}

	public void setSpeakingDateTime(String speakingDateTime) {
		this.speakingDateTime = speakingDateTime;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public Integer getCurrentIdentity() {
		return currentIdentity;
	}

	public void setCurrentIdentity(Integer currentIdentity) {
		this.currentIdentity = currentIdentity;
	}


}
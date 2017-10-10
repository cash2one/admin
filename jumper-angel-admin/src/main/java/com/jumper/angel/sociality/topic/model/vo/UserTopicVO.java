package com.jumper.angel.sociality.topic.model.vo;

import java.util.Date;

import com.jumper.angel.sociality.topic.model.po.UserTopicPO;

/**
 * 
* @ClassName: UserTopic 
* @Description: 用户话题关联信息
* @author caishiming
* @date 2016年10月22日 上午11:14:51
 */
public class UserTopicVO extends UserTopicPO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5180164542394535643L;
	

    /**
     * 格式化后的日期格式
     */
    private String createDateTime;
    /**
     * 最后一次发帖时间
     */
    private String debatepostDateTime;
    
    /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String mobile;
    
    private Date createDate;
    
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDebatepostDateTime() {
		return debatepostDateTime;
	}
	public void setDebatepostDateTime(String debatepostDateTime) {
		this.debatepostDateTime = debatepostDateTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
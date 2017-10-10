package com.jumper.angel.sociality.debatepost.model.vo;

import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;

/**
 * 
* @ClassName: Debatepost 
* @Description: 帖子实体
* @author caishiming
* @date 2016年10月22日 下午3:31:31
 */
public class DebatepostVO extends DebatepostPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4709685745380279381L;
	
    /**
     * 发帖时间
     */
    private String debatepostDateTime;
    
    /**
     * 删除时间
     */
    private String deletedTime;
    /**
     * 从第几页
     */
    private Integer beginIndex;
    /**
     * 每页显示多少条数据
     */
    private Integer everyPage;
    /**
     * 查询关键字
     */
    private String keyword;
    /**
     * 查询状态
     */
    private Integer status;
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 删帖人员
     */
    private String deletedAdmin;
    
	public String getDebatepostDateTime() {
		return debatepostDateTime;
	}
	public void setDebatepostDateTime(String debatepostDateTime) {
		this.debatepostDateTime = debatepostDateTime;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(String deletedTime) {
		this.deletedTime = deletedTime;
	}
	public String getDeletedAdmin() {
		return deletedAdmin;
	}
	public void setDeletedAdmin(String deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}
	
}
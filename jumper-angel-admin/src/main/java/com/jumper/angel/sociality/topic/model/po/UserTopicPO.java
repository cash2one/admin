package com.jumper.angel.sociality.topic.model.po;
/**
 * 
* @ClassName: UserTopic 
* @Description: 用户话题关联信息
* @author caishiming
* @date 2016年10月22日 上午11:14:51
 */
public class UserTopicPO implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5180164542394535643L;
	private Long id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 话题ID
     */
    private Long topicId;
    /**
	 * 话题名称
	 */
	private String topicName;
    /**
     * 
     */
    private Long createTime;
    /**
     * 最后发帖时间
     */
    private Long debatepostTime;
    /**
     * 是否禁止发帖
     */
    private Integer isBlacklist;
    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 话题列表
     */
    private String topicIds;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDebatepostTime() {
        return debatepostTime;
    }

    public void setDebatepostTime(Long debatepostTime) {
        this.debatepostTime = debatepostTime;
    }

    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicIds() {
		return topicIds;
	}

	public void setTopicIds(String topicIds) {
		this.topicIds = topicIds;
	}
    
}
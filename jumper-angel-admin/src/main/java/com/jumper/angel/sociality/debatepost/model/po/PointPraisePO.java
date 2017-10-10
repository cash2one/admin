package com.jumper.angel.sociality.debatepost.model.po;
/**
 * 
* @ClassName: PointPraise 
* @Description: 点赞实体
* @author caishiming
* @date 2016年10月24日 上午9:34:47
 */
public class PointPraisePO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8462435546280731299L;
	/**
	 * 点赞id
	 */
    private Long pointPraiseId;
    /**
	 * 用户ID
	 */
    private String pointPraiseUserId;
    /**
	 * 帖子ID
	 */
    private Long debatepostId;
    /**
	 * 创建时间
	 */
    private Long credteDate;
    
    /** 创建时间 */
    private String createTime;

    public Long getPointPraiseId() {
        return pointPraiseId;
    }

    public void setPointPraiseId(Long pointPraiseId) {
        this.pointPraiseId = pointPraiseId;
    }

    public String getPointPraiseUserId() {
        return pointPraiseUserId;
    }

    public void setPointPraiseUserId(String pointPraiseUserId) {
        this.pointPraiseUserId = pointPraiseUserId == null ? null : pointPraiseUserId.trim();
    }

    public Long getDebatepostId() {
        return debatepostId;
    }

    public void setDebatepostId(Long debatepostId) {
        this.debatepostId = debatepostId;
    }

    public Long getCredteDate() {
        return credteDate;
    }

    public void setCredteDate(Long credteDate) {
        this.credteDate = credteDate;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
    
}
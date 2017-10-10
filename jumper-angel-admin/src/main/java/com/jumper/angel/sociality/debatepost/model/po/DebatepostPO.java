package com.jumper.angel.sociality.debatepost.model.po;
/**
 * 
* @ClassName: Debatepost 
* @Description: 帖子实体
* @author caishiming
* @date 2016年10月22日 下午3:31:31
 */
public class DebatepostPO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782368206539106069L;
	/**
	 * 帖子ID
	 */
    private Long debatepostId;
    /**
	 * 话题ID
	 */
    private Long topicId;
    /**
	 * 话题名称
	 */
	private String topicName;
    /**
	 * 帖子主题
	 */
    private String debatepostTitle;
    /**
	 * 帖子内容
	 */
    private String debatepostContent;
    /**
	 * 发表者ID
	 */
    private String debatepostUserId;
    /**
	 * 是否热门
	 */
    private Integer whetherPopular;
    /**
	 * 图片
	 */
    private String img;
    /**
	 * 点赞数
	 */
    private Integer pointPraise;
    /**
	 * 评论数
	 */
    private Integer commentNumber;
    /**
     * 阅读量
     */
    private Integer readNumber;
    /**
	 * 创建时间
	 */
    private Long createTime;
    /**
	 * 举报次数
	 */
    private Integer reportCount;
    /**
	 * 举报总次数
	 */
    private Integer numberReports;
    /**
	 * 是否删除
	 */
    private Integer isDelete;

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
        this.debatepostTitle = debatepostTitle == null ? null : debatepostTitle.trim();
    }

    public String getDebatepostContent() {
        return debatepostContent;
    }

    public void setDebatepostContent(String debatepostContent) {
        this.debatepostContent = debatepostContent == null ? null : debatepostContent.trim();
    }

    public String getDebatepostUserId() {
        return debatepostUserId;
    }

    public void setDebatepostUserId(String debatepostUserId) {
        this.debatepostUserId = debatepostUserId == null ? null : debatepostUserId.trim();
    }

    public Integer getWhetherPopular() {
        return whetherPopular;
    }

    public void setWhetherPopular(Integer whetherPopular) {
        this.whetherPopular = whetherPopular;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getPointPraise() {
        return pointPraise;
    }

    public void setPointPraise(Integer pointPraise) {
        this.pointPraise = pointPraise;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getNumberReports() {
        return numberReports;
    }

    public void setNumberReports(Integer numberReports) {
        this.numberReports = numberReports;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    } 


	public Integer getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
package com.jumper.angel.sociality.debatepost.model.vo;

import com.jumper.angel.sociality.debatepost.model.po.UserCollectionPO;

/**
* @ClassName: UserCollection 
* @Description: 收藏表
* @author caishiming
* @date 2016年10月24日 下午6:13:22
 */
public class UserCollectionVO extends UserCollectionPO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6352146345487273083L;
	private Long collecId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 帖子ID
     */
    private Long debatepostId;
    /**
     * 创建日期
     */
    private Long credteDate;

    public Long getCollecId() {
        return collecId;
    }

    public void setCollecId(Long collecId) {
        this.collecId = collecId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}
package com.jumper.angel.sociality.group.model.bo;

import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;

public class SocialityGroupBo extends SocialityGroupPo implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1978092123654145L;
	
	
	
    /**
     * 格式化后的日期格式
     */
    private String createDateTime;
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}


}
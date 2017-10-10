package com.jumper.angel.sociality.group.model.vo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;


public class SocialityGroupVo extends SocialityGroupPo implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1978092123654145L;

	/** 页码 */
	private Integer page;
	/** 每页条数 */
	private Integer rows;
	/**
     * 话题名称的拼音
     */
    private String pingYin;
    
    /**
     * 格式化后的日期格式
     */
    private String createDateTime;

	public Integer getPage() {
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
	}
    public String getPingYin() {
		return pingYin;
	}

	public void setPingYin(String pingYin) {
		this.pingYin = pingYin;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	
	
}
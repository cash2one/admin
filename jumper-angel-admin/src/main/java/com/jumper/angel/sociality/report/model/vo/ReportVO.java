package com.jumper.angel.sociality.report.model.vo;

import com.jumper.angel.sociality.report.model.po.ReportPO;

/**
 * 社交 举报中心实体类
 * @ClassName: ReportVo  
 * @author huangzg 2016年12月24日 上午9:58:01
 */
public class ReportVO extends ReportPO implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1979006808066050L;
	/**
	 * 操作状态 1恢复隐藏 2 删除 3禁用 4 解除禁言 5 正常
	 */
	private int operatorType;
	
	private String strDateTime;
	
	private String userNickName;
	
	private String groupId;
	
	
	public int getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(int operatorType) {
		this.operatorType = operatorType;
	}
	public String getStrDateTime() {
		return strDateTime;
	}
	public void setStrDateTime(String strDateTime) {
		this.strDateTime = strDateTime;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
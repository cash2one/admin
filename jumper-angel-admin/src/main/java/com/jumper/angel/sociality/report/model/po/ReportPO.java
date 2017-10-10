package com.jumper.angel.sociality.report.model.po;

/**
 * 社交 举报中心实体类
 * @ClassName: ReportPo  
 * @author huangzg 2016年12月24日 上午9:58:01
 */
public class ReportPO implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1979006808066048L;

	/** 举报ID */
	private java.lang.Long reportId;
	/** 业务ID */
	private java.lang.String busId;
	/** 举报原因 */
	private java.lang.String reportContent;
	/** 举报人ID */
	private java.lang.String reportUserId;
	/** 举报记录时间 */
	private java.lang.Long reportTime;
	/** 数据类型(1: 帖子 2：评论 3：用户) */
	private java.lang.Long dataType;
	/** 数据状态 */
	private java.lang.Long dataState;
	/**
	 * 被举报消息,交流圈被举报的消息
	 */
	private String beReportMessage;
	/**
	 * 被举报用户所属圈子
	 */
	private String groupId;

	public java.lang.Long getReportId() {
		return reportId;
	}

	public void setReportId(java.lang.Long reportId) {
		this.reportId = reportId;
	}

	public java.lang.String getBusId() {
		return busId;
	}

	public void setBusId(java.lang.String busId) {
		this.busId = busId;
	}

	public java.lang.String getReportContent() {
		return reportContent;
	}

	public void setReportContent(java.lang.String reportContent) {
		this.reportContent = reportContent;
	}

	public java.lang.String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(java.lang.String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public java.lang.Long getReportTime() {
		return reportTime;
	}

	public void setReportTime(java.lang.Long reportTime) {
		this.reportTime = reportTime;
	}

	public java.lang.Long getDataType() {
		return dataType;
	}

	public void setDataType(java.lang.Long dataType) {
		this.dataType = dataType;
	}

	public java.lang.Long getDataState() {
		return dataState;
	}

	public void setDataState(java.lang.Long dataState) {
		this.dataState = dataState;
	}

	public String getBeReportMessage() {
		return beReportMessage;
	}

	public void setBeReportMessage(String beReportMessage) {
		this.beReportMessage = beReportMessage;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
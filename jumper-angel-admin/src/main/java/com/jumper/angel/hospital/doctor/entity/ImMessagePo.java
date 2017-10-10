package com.jumper.angel.hospital.doctor.entity;

/**
 * 聊天记录Po类
 * 
 * @ClassName: ImMessagePo
 * @author huangzg 2017年2月28日 上午9:46:19
 */
public class ImMessagePo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 2072451443705856L;

	private Long id;
	/** 消息ID */
	private Long msgId;
	/** 业务代码 */
	private String busCode;
	/** 服务ID/问题ID */
	private String consultantId;
	/** 发送者ID */
	private String sendChatId;
    /**发送者name*/
    private String sendChatName;
	/** 接收者ID */
	private String recevrerChatId;
	/** 发送成功入库时间 */
	private String sendTime;
	/** 消息类型 */
	private String msgType;
	/** 业务数据ID */
	private String dataId;
	/** 数据类型 1：普通聊天消息 2：系统消息 3：业务通知消息 */
	private Long dataType;
	/** 消息内容 */
	private String msgContent;
	/** 创建时间 */
	private Long createDate;
	/** 消息状态 1：已读 2：未读 */
	private Long msgDatastatus;
	/** 消息回调时间 */
	private Long callbackTime;
	/** 发送时消息ID，作为标识 */
	private Long sendMsgId;
	/** 语音消息大小 */
	private String size;
	/** 群号 */
	private String groupId;
	/** 图片访问路径 */
	private String filePath;
	
	//图片大小
	/**图片高度*/
	private Integer height;
	/**图片宽度*/
	private Integer width;

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public ImMessagePo() {
		super();
	}

	public ImMessagePo(Long msgId, String busCode, String consultantId,
			String sendChatId, String recevrerChatId, String sendTime,
			String msgType, String dataId, Long dataType, String msgContent,
			Long createDate, Long msgDatastatus, Long callbackTime,
			Long sendMsgId, String size, String groupId) {
		super();
		this.msgId = msgId;
		this.busCode = busCode;
		this.consultantId = consultantId;
		this.sendChatId = sendChatId;
		this.recevrerChatId = recevrerChatId;
		this.sendTime = sendTime;
		this.msgType = msgType;
		this.dataId = dataId;
		this.dataType = dataType;
		this.msgContent = msgContent;
		this.createDate = createDate;
		this.msgDatastatus = msgDatastatus;
		this.callbackTime = callbackTime;
		this.sendMsgId = sendMsgId;
		this.size = size;
		this.groupId = groupId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSendChatName() {
		return sendChatName;
	}

	public void setSendChatName(String sendChatName) {
		this.sendChatName = sendChatName;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	public String getSendChatId() {
		return sendChatId;
	}

	public void setSendChatId(String sendChatId) {
		this.sendChatId = sendChatId;
	}

	public String getRecevrerChatId() {
		return recevrerChatId;
	}

	public void setRecevrerChatId(String recevrerChatId) {
		this.recevrerChatId = recevrerChatId;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Long getDataType() {
		return dataType;
	}

	public void setDataType(Long dataType) {
		this.dataType = dataType;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getMsgDatastatus() {
		return msgDatastatus;
	}

	public void setMsgDatastatus(Long msgDatastatus) {
		this.msgDatastatus = msgDatastatus;
	}

	public Long getCallbackTime() {
		return callbackTime;
	}

	public void setCallbackTime(Long callbackTime) {
		this.callbackTime = callbackTime;
	}

	public Long getSendMsgId() {
		return sendMsgId;
	}

	public void setSendMsgId(Long sendMsgId) {
		this.sendMsgId = sendMsgId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
package com.jumper.angel.hospital.doctor.entity;
/**消息记录对象*/
public class IMMessage {
    private Long id;
    /**消息ID*/
    private Long msgId;
    /**业务代码*/
    private String busCode;
    /**服务ID/问题ID*/
    private String consultantId;
    /**发送者ID*/
    private String sendChatId;
    /**发送者name*/
    private String sendChatName;
    /**接收者ID*/
    private String recevrerChatId;
    /**发送时间*/
    private String sendTime;
    /**消息类型*/
    private String msgType;
    /**业务数据ID*/
    private String dataId;
    /**数据类型 1：普通聊天消息 2：系统消息 3：业务通知消息*/
    private Long dataType;
    /**消息内容*/
    private String msgContent;
    /**创建时间*/
    private Long createDate;
    /**消息状态 1：已读 2：未读*/
    private Long msgDatastatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.busCode = busCode == null ? null : busCode.trim();
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId == null ? null : consultantId.trim();
    }

    public String getSendChatName() {
		return sendChatName;
	}

	public void setSendChatName(String sendChatName) {
		this.sendChatName = sendChatName;
	}

	public String getSendChatId() {
        return sendChatId;
    }

    public void setSendChatId(String sendChatId) {
        this.sendChatId = sendChatId == null ? null : sendChatId.trim();
    }

    public String getRecevrerChatId() {
        return recevrerChatId;
    }

    public void setRecevrerChatId(String recevrerChatId) {
        this.recevrerChatId = recevrerChatId == null ? null : recevrerChatId.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
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
        this.msgContent = msgContent == null ? null : msgContent.trim();
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
}
package com.jumper.angel.hospital.doctor.entity;
/**医生订单信息*/
public class DoctorOrderInfo {
    private Integer id;
    /**医生ID*/
    private Integer doctorId;
    /**用户ID*/
    private Integer userId;
    /**hospital_doctor_services id*/
    private Integer serviceId;
    /**购买金额*/
    private String buyCost;
    /**服务开始时间*/
    private String serviceStartTime;
    /**服务结束时间*/
    private String serviceEndTime;
    /**订单状态 0 正常 1结束 2待确认 3关闭 4已经拒绝*/
    private String orderStatus;
    /**支付状态  0：未支付  1：已支付  2：已关闭  3：申请退款中  4：退款成功  5：退款拒绝  6:申诉中  7:申诉成功并退款  8:申诉失败  9:申诉成功但还未退款  10:同意退款*/
    private String payStatus;
    /**添加时间*/
    private String addTime;
    /**私人医生特有的类型 1 周 2月*/
    private String buyTimeType;
    /**服务的类型, 0:义诊1:图文咨询,2:医院问诊,3:胎心监护,4:在线问诊,5:私人医生,6:网络诊室,7:设备租赁,8:胎心解读*/
    private String serviceType;
    /**回复id*/
    private Integer consultantId;
    /**订单号*/
    private String orderNo;
    /**支付订单号*/
    private String payOrderNo;
    /**发送推送信息状态 0 未发送 1 已发送*/
    private Integer sendType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(String buyCost) {
        this.buyCost = buyCost == null ? null : buyCost.trim();
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime == null ? null : serviceStartTime.trim();
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime == null ? null : serviceEndTime.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getBuyTimeType() {
        return buyTimeType;
    }

    public void setBuyTimeType(String buyTimeType) {
        this.buyTimeType = buyTimeType == null ? null : buyTimeType.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public Integer getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Integer consultantId) {
        this.consultantId = consultantId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
}
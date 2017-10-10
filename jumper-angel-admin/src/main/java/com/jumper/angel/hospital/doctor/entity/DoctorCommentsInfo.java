package com.jumper.angel.hospital.doctor.entity;

import java.util.Date;
/**评论相关信息对象*/
public class DoctorCommentsInfo {
	private Integer id;
	/**医生id*/
    private Integer doctorId;
    /**用户id*/
    private Integer userId;
    /**评论内容*/
    private String content;
    /**满意度*/
    private Integer satisfaction;
    /**添加时间*/
    private Date addTime;
    /**评论id*/
    private Integer constantId;
    /**类型*/
    private Integer type;
    /**是否关闭：1：不显示；0：显示*/
    private Integer status;
    /**显示评论*/
    public static final Integer OPEN_STATUS = 0;
    /**不显示评论*/
    public static final Integer CLOES_STATUS = 1;
    
    public Integer getDoctorId() {
        return doctorId;
    }

    
    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getConstantId() {
        return constantId;
    }

    public void setConstantId(Integer constantId) {
        this.constantId = constantId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
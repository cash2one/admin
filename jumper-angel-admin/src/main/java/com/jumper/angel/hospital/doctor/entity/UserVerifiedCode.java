package com.jumper.angel.hospital.doctor.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 验证码entity
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-2-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class UserVerifiedCode implements Serializable {
    
	private static final long serialVersionUID = -600086266507077016L;

	private Integer id;

    private String mobile;

    private Date addTime;

    private String code;
    //返回到前端的添加时间
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	public String getCreateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTime = format.format(this.getAddTime());
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
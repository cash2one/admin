package com.jumper.angel.home.pregnancy.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 孕期产检项目信息实体bean
 * @Description TODO
 * @author fangxilin
 * @date 2016-11-30
 */
public class PregnantAntenatalExaminationItemInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer examinationInfoId;//产检次数id
	private String name;//产检项目名称
	private String content;//详情描述
	private Timestamp addTime;//编辑时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExaminationInfoId() {
		return examinationInfoId;
	}
	public void setExaminationInfoId(Integer examinationInfoId) {
		this.examinationInfoId = examinationInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}

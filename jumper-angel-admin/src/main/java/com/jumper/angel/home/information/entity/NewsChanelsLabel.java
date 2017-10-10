package com.jumper.angel.home.information.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 新闻频道标签实体 entity
 * @author gyx
 * @time 2016年12月28日
 */
public class NewsChanelsLabel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	/** 标签名称 **/
	private String label;
	/** 所属频道 **/
	private Integer chanelId;
	/** 添加时间 **/
	private Timestamp addTime;

	/*********************************getter and setter*************************************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getChanelId() {
		return chanelId;
	}
	public void setChanelId(Integer chanelId) {
		this.chanelId = chanelId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}

package com.jumper.angel.home.pregnancy.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 产检项目封装实体
 * @author gyx
 * @time 2016年12月27日
 */
public class VOPregAnteExamItemInfo implements Serializable{
	private static final long serialVersionUID = -2703212412444085694L;
	private Integer id;//产检项目id
	private Integer examinationInfoId;//产检次数id
	private Integer examinationNumbers;//产检次数
	private String name;//产检项目名称
	private String content;//详情描述
	private String addTime;//编辑时间
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
	public Integer getExaminationNumbers() {
		return examinationNumbers;
	}
	public void setExaminationNumbers(Integer examinationNumbers) {
		this.examinationNumbers = examinationNumbers;
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
	
}

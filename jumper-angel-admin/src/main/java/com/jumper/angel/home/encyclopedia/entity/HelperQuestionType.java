package com.jumper.angel.home.encyclopedia.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 常见问题标签 entity
 * @author gyx
 * @time 2016年12月1日
 */
public class HelperQuestionType implements Serializable {
	
	private static final long serialVersionUID = -8417400826980425678L;

	private Integer id;
	//常见问题的标签名称
	private String name;
	//标签的图片URL地址
	private String imageUrl;
	//该标签是否可见 ：0 可见 ，1 不可见
	private Integer isVisible;
	//添加时间
	private Timestamp addTime;
	//模块id
	private Integer parentId;
	//模块名称
	private String className;
	
	private String typeAddTime;

	public HelperQuestionType() {
	}

	public HelperQuestionType(String name, Timestamp addTime) {
		this.name = name;
		this.addTime = addTime;
	}

	public HelperQuestionType(HelperQuestionClass helperQuestionClass,
			String name, String imageUrl, Integer isVisible, Timestamp addTime, Integer parentId) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.isVisible = isVisible;
		this.addTime = addTime;
		this.parentId = parentId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTypeAddTime() {
		return typeAddTime;
	}

	public void setTypeAddTime(String typeAddTime) {
		this.typeAddTime = typeAddTime;
	}
	
	
}
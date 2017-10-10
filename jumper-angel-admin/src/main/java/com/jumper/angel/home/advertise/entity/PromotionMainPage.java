package com.jumper.angel.home.advertise.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 首页推广实体
 * @author gyx
 * @time 2017年2月7日
 */
public class PromotionMainPage  implements Serializable {
	
	private static final long serialVersionUID = -8417400826980425678L;

    private Integer id;
    //推广的类型：1:广告,2:论坛贴子,3:商品,4:医生信息,....
    private Integer typeId;
    //分别对应实体的id
    private Integer objectId;
    //排列的序号
    private Integer sort;
    //添加时间
    private Timestamp addTime;
    //广告描述
    private String description;
    //显示位置，1：首页广告，2：医生页广告
    private Integer status;
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getSort() {
        return this.sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

}
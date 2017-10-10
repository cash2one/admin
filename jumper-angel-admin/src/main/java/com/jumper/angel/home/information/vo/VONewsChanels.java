package com.jumper.angel.home.information.vo;

import java.io.Serializable;

import com.jumper.angel.utils.ConfigProUtils;

/**
 * 新闻频道封装实体
 * @author gyx
 * @time 2016年12月28日
 */
public class VONewsChanels implements Serializable{
	private static final long serialVersionUID = -3704996589398370078L;

	private Integer id;

	//频道名字
    private String chanelName;

    //频道描述
    private String channelDesc;

    //新用户是否默认订阅此频道
    private Boolean isDefaultSub;
    
    //频道图片
    private String imgUrl;
    
    //频道标签
    private String chanelLabels;
    
    //频道位置
    private Integer orderBy;
    
    //文件访问前缀
    private String prefix = ConfigProUtils.get("file_path");

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChanelName() {
		return chanelName;
	}

	public void setChanelName(String chanelName) {
		this.chanelName = chanelName;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	public Boolean getIsDefaultSub() {
		return isDefaultSub;
	}

	public void setIsDefaultSub(Boolean isDefaultSub) {
		this.isDefaultSub = isDefaultSub;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getChanelLabels() {
		return chanelLabels;
	}

	public void setChanelLabels(String chanelLabels) {
		this.chanelLabels = chanelLabels;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
    
    
    
    

}

package com.jumper.angel.home.information.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 频道Entity
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-1
 */
public class NewsChanels implements Serializable {
	
	private static final long serialVersionUID = -3704996589398370078L;

	private Integer id;

	//频道名字
    private String chanelName;

    //添加时间
    private Date addTime;

    //频道排序
    private Integer orderBy;

    //频道描述
    private String channelDesc;

    //频道图标
    private String imgUrl;

    //频道版本
    private Integer channelVer;

    //频道被订阅数量
    private Integer subNum;

    //新用户是否默认订阅此频道
    private Boolean isDefaultSub;

    //是否视频
    private Boolean isVideo;

    private Integer hospitalId;

    //新增的频道用户是否可以订阅(隐藏与显示),0:否,1:是
    private Byte state;

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
        this.chanelName = chanelName == null ? null : chanelName.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc == null ? null : channelDesc.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getChannelVer() {
        return channelVer;
    }

    public void setChannelVer(Integer channelVer) {
        this.channelVer = channelVer;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public Boolean getIsDefaultSub() {
        return isDefaultSub;
    }

    public void setIsDefaultSub(Boolean isDefaultSub) {
        this.isDefaultSub = isDefaultSub;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
package com.jumper.angel.home.information.vo;

import java.util.Date;

/**
 * 新闻文章封装类 vo
 * @author gyx
 * @time 2017年1月5日
 */
public class VONewsInformation {
	private Integer id = 0;
	//标题
	private String title = "";
	//详情URL
	private String newsUrl = "";
	//频道id
	private Integer channelId = 0;
	//频道名称
	private String channelName = "";
	//首页图片
	private String imageUrl = "";
	//总阅读量
	private Integer clicks = 0;
	//昨日阅读量
	private Integer yesterdayClicks = 0;
	//上周阅读量
	private Integer lastWeekClicks = 0;
	//上月阅读量
	private Integer lastMonthClicks = 0;
	//分享量
	private Integer shareNum = 0;
	//评论数
	private Integer commentNum = 0;
	//目标用户
	private String period = "";
	//是否发布
	private Boolean isPublish = false;
	//添加时间
	private String addTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public Integer getYesterdayClicks() {
		return yesterdayClicks;
	}
	public void setYesterdayClicks(Integer yesterdayClicks) {
		this.yesterdayClicks = yesterdayClicks;
	}
	public Integer getLastWeekClicks() {
		return lastWeekClicks;
	}
	public void setLastWeekClicks(Integer lastWeekClicks) {
		this.lastWeekClicks = lastWeekClicks;
	}
	public Integer getLastMonthClicks() {
		return lastMonthClicks;
	}
	public void setLastMonthClicks(Integer lastMonthClicks) {
		this.lastMonthClicks = lastMonthClicks;
	}
	public Integer getShareNum() {
		return shareNum;
	}
	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Boolean getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Boolean isPublish) {
		this.isPublish = isPublish;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}

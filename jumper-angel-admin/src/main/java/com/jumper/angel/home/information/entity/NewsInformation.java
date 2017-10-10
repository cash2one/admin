package com.jumper.angel.home.information.entity;

import java.io.Serializable;
import java.util.Date;

import com.jumper.angel.utils.ConfigProUtils;
/**
 * 资讯信息Entity
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-1
 */
public class NewsInformation implements Serializable {
    
	private static final long serialVersionUID = -7106686341518400070L;

	private Integer id;

	//所属频道id
    private Integer channelId;

    //所属专题
    private Integer specialTopicId;

    //标题
    private String title;

    //生成新闻的静态的URL地址
    private String newsUrl;

    //新闻的首页图片
    private String imageUrl;

    //是否是频道的焦点图
    private Integer isFocusImage;

    //添加时间
    private Date addTime;

    //点赞
    private Integer clicks;

    //是否推送
    private Integer isPush;

    //内容简介
    private String introduct;

    //文章来源图标url
    private String fromLogoUrl;

    //文章来源
    private String sourceFrom;

    //关键字
    private String keywords;

    //是否发布
    private Integer isPublish;

    //分享数
    private Integer shareNum;

    //被收藏数
    private Integer praise;

    //对应怀孕阶段（  1：孕早期     2：孕中期     3：孕晚期     4：0-6个月     5:6月-1岁     6:1-3岁）
    private String period;

    private Integer hospitalId;

    //置顶根据时间排序
    private Date top;
    
    //发布时间    
    private Date publishTime;

    //新闻内容
    private String content;
    
    //图片地址前缀
    private String imagePrefix = ConfigProUtils.get("file_path");

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getSpecialTopicId() {
        return specialTopicId;
    }

    public void setSpecialTopicId(Integer specialTopicId) {
        this.specialTopicId = specialTopicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl == null ? null : newsUrl.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getIsFocusImage() {
        return isFocusImage;
    }

    public void setIsFocusImage(Integer isFocusImage) {
        this.isFocusImage = isFocusImage;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct == null ? null : introduct.trim();
    }

    public String getFromLogoUrl() {
        return fromLogoUrl;
    }

    public void setFromLogoUrl(String fromLogoUrl) {
        this.fromLogoUrl = fromLogoUrl == null ? null : fromLogoUrl.trim();
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom == null ? null : sourceFrom.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getTop() {
        return top;
    }

    public void setTop(Date top) {
        this.top = top;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getImagePrefix() {
		return imagePrefix;
	}

	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
}
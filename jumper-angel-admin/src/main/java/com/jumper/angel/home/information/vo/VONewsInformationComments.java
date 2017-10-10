package com.jumper.angel.home.information.vo;

import java.io.Serializable;
/**
 * 评论封装 vo
 * @author gyx
 * @time 2017年1月7日
 */
public class VONewsInformationComments implements Serializable {
    
	private static final long serialVersionUID = 3693370298175784119L;

	private Integer id;

    //新闻文章id
    private Integer newsInformationId;

    //用户id
    private Integer userId;
    
    //用户名
    private String userName;

    //评论内容
    private String content;

    //添加时间
    private String addTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsInformationId() {
        return newsInformationId;
    }

    public void setNewsInformationId(Integer newsInformationId) {
        this.newsInformationId = newsInformationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

   
}
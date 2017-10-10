package com.jumper.angel.home.information.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 阅读量 entity
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-2
 */
public class NewsClick implements Serializable {
   
	private static final long serialVersionUID = 1833760157748278628L;

	private Integer id;

	//新闻文章id
    private Integer newsId;

    //点击量
    private Integer clicks;

    //添加时间
    private Date addDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
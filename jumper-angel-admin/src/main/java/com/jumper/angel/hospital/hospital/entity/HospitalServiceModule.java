package com.jumper.angel.hospital.hospital.entity;
/**
 * 服务模块
*
* HospitalServiceModule
*jumper-doctor
* 2017年6月6日 上午11:13:45
*
* @version 1.0.0
 */
public class HospitalServiceModule {
    private Integer id;
    /**创建日期**/
    private Long createdDate;
    /**服务名称**/
    private String title;
    /**备注**/
    private String comment;
    /**模块默认图标**/
    private String imgUrl;
    /**是否有效：0有效，1禁用，默认为0**/
    private Integer disabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
}
package com.jumper.angel.hospital.hospital.vo;
/**
 * 
*医院服务对象
* HospitalServiceList
*Demo
* 2017年6月6日 上午11:07:36
*
* @version 1.0.0
 */
public class HospitalServiceVo {
    private Integer id;
    /**创建日期**/
    private Long createdDate;
    /**医院id**/
    private Integer hospitalId;
    /**服务模块id**/
    private Integer moduleId;
    /**备注**/
    private String comment;
    /**功能分组:0基础功能;1平台功能;2院内功能**/
    private Integer functionGroup;
    /**服务状态:0未关闭，1关闭的，默认为0**/
    private Integer closed;
    /**位置排序值**/
    private Integer postionOrder;
    /**入口状态:0正常;1隐藏的**/
    private Integer entryStat;
    /**默认h5链接地址**/
    private String defaultUrl;
    /**h5链接地址**/
    private String url;
    /**是否原生应用:0h5应用，1原生，默认为0**/
    private Integer nativeApp;
    /**模块图标**/
    private String iconImg;
    /**链接状态:0默认;1自定义**/
    private Integer urlStat;
    
    /**服务名称**/
    private String title;
    /**是否有效：0有效，1禁用，默认为0**/
    private Integer disabled;
    /**模块默认图标**/
    private String imgUrl;
    
    /**医院名称**/
    private String hospitalName;
    
    

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

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getFunctionGroup() {
        return functionGroup;
    }

    public void setFunctionGroup(Integer functionGroup) {
        this.functionGroup = functionGroup;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getPostionOrder() {
        return postionOrder;
    }

    public void setPostionOrder(Integer postionOrder) {
        this.postionOrder = postionOrder;
    }

    public Integer getEntryStat() {
        return entryStat;
    }

    public void setEntryStat(Integer entryStat) {
        this.entryStat = entryStat;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl == null ? null : defaultUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getNativeApp() {
        return nativeApp;
    }

    public void setNativeApp(Integer nativeApp) {
        this.nativeApp = nativeApp;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public String getIconImg() {
		return iconImg;
	}

	public void setIconImg(String iconImg) {
		this.iconImg = iconImg;
	}

	public Integer getUrlStat() {
		return urlStat;
	}

	public void setUrlStat(Integer urlStat) {
		this.urlStat = urlStat;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
}
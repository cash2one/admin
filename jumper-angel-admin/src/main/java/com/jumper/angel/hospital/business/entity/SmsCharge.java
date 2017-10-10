package com.jumper.angel.hospital.business.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @ClassName: SmsContent
* @Description: 收费短信实体
* @author liump
* @date 2017年4月25日 下午2:52:15
* @Modifier:
* @modifydate:
*/
public class SmsCharge implements Serializable{
	/**
	 * 类的版本号
	 */
	private static final long serialVersionUID = -401331563799511073L;
	
	/** ID */
	private Long id;
	
	/**
	 * 医院ID
	 */
	private Long hospId;
	/**
	 * 医院名称
	 */
	private String hospName;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 参数签名
	 */
	private String req_sign;
	
	/**
	 * app来源编号
	 */
	private String appid;
	
	/**
	 * 业务渠道模块名称
	 */
	private String appName;
	
	/**
	 * 短信发送时间
	 */
	private Date sendTime;
	
	/**
	 * 短信发送是否成功，0：失败，1：成功
	 */
	private int isSuccess;
	
	/**
	 * 发送人
	 */
	private String msgFrom;
	
	/** 创建时间   用于在页面显示*/
	private String createTime;
	
	/**  
	 * @Title:  getHospId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: hospId <BR>  
	 */
	public Long getHospId() {
		return hospId;
	}
	
	/**  
	 * @Title:  setHospId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.hospId= hospId<BR>  
	 */
	public void setHospId(Long hospId) {
		this.hospId= hospId;
	}
	
	/**  
	 * @Title:  getHospName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: hospName <BR>  
	 */
	public String getHospName() {
		return hospName;
	}
	
	/**  
	 * @Title:  setHospName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.hospName= hospName<BR>  
	 */
	public void setHospName(String hospName) {
		this.hospName= hospName;
	}
	
	/**  
	 * @Title:  getContent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: content <BR>  
	 */
	public String getContent() {
		return content;
	}
	
	/**  
	 * @Title:  setContent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.content= content<BR>  
	 */
	public void setContent(String content) {
		this.content= content;
	}
	
	/**  
	 * @Title:  getMobile <BR>  
	 * @Description: please write your description <BR>  
	 * @return: mobile <BR>  
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**  
	 * @Title:  setMobile <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.mobile= mobile<BR>  
	 */
	public void setMobile(String mobile) {
		this.mobile= mobile;
	}
	
	/**  
	 * @Title:  getReq_sign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: req_sign <BR>  
	 */
	public String getReq_sign() {
		return req_sign;
	}
	
	/**  
	 * @Title:  setReq_sign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.req_sign= req_sign<BR>  
	 */
	public void setReq_sign(String req_sign) {
		this.req_sign= req_sign;
	}

	/**  
	 * @Title:  getAppid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: appid <BR>  
	 */
	public String getAppid() {
		return appid;
	}

	/**  
	 * @Title:  setAppid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.appid= appid<BR>  
	 */
	public void setAppid(String appid) {
		this.appid= appid;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**用于在页面显示*/
	/*public String getCreateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTime = format.format(this.getSendTime());
		return createTime;
	}*/
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}

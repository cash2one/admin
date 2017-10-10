package com.jumper.angel.hospital.business.entity;

import java.io.Serializable;

/**
* @ClassName: SmsRecord
* @Description: 短信统计实体
* @author liump
* @date 2017年4月28日 下午2:42:14
* @Modifier:
* @modifydate:
*/
public class SmsRecord implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 9031580075928535428L;
	/**
	 * 医院名称
	 */
	private String hospName;
	/**
	 * 医院id
	 */
	private Long hospId;
	/**
	 * 发出短信条数
	 */
	private Long totalSend;
	/**
	 * 发送成功条数
	 */
	private Long totalSuccess;
	/**
	 * 发送失败条数
	 */
	private Long totalFailure;
	
	/**
	 * 业务渠道模块ID
	 */
	private String appid;
	
	/**
	 * 业务渠道模块名称
	 */
	private String appName;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 发送人
	 */
	private String msgFrom;
	
	public SmsRecord() {
		super();
	}
	
	public SmsRecord(String hospName, Long hospId, Long totalSend, Long totalSuccess, Long totalFailure) {
		super();
		this.hospName = hospName;
		this.hospId = hospId;
		this.totalSend = totalSend;
		this.totalSuccess = totalSuccess;
		this.totalFailure = totalFailure;
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
	 * @Title:  getTotalSend <BR>  
	 * @Description: please write your description <BR>  
	 * @return: totalSend <BR>  
	 */
	public Long getTotalSend() {
		return totalSend;
	}

	/**  
	 * @Title:  setTotalSend <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.totalSend= totalSend<BR>  
	 */
	public void setTotalSend(Long totalSend) {
		this.totalSend= totalSend;
	}

	/**  
	 * @Title:  getTotalSuccess <BR>  
	 * @Description: please write your description <BR>  
	 * @return: totalSuccess <BR>  
	 */
	public Long getTotalSuccess() {
		return totalSuccess;
	}

	/**  
	 * @Title:  setTotalSuccess <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.totalSuccess= totalSuccess<BR>  
	 */
	public void setTotalSuccess(Long totalSuccess) {
		this.totalSuccess= totalSuccess;
	}

	/**  
	 * @Title:  getTotalFailure <BR>  
	 * @Description: please write your description <BR>  
	 * @return: totalFailure <BR>  
	 */
	public Long getTotalFailure() {
		return totalFailure;
	}

	/**  
	 * @Title:  setTotalFailure <BR>  
	 * @Description: please write your description <BR>  
	 * @return: this.totalFailure= totalFailure<BR>  
	 */
	public void setTotalFailure(Long totalFailure) {
		this.totalFailure= totalFailure;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	
}

package com.jumper.angel.hospital.doctor.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
/**
 * 医院咨询信息
*
* HospitalConsultant
*jumper-doctor
* 2016年12月21日 下午5:58:36
* @version 1.0.0
 */
@SuppressWarnings("rawtypes")
public class HospitalConsultantInfo implements java.io.Serializable {
	private static final long serialVersionUID = 2696531439171886096L;
	private Integer id;
	// Fields
	/** 用户Id */
	private Integer userId;
	/** 用户对象 */
	private UserInfoBean userInfo;
	/** 医生id */
	private Integer doctorId;
	/**医生对象 */
	private HospitalDoctorInfo hospitalDoctorInfo;
	/**评论信息对象 */
	private DoctorCommentsInfo doctorComments;
	/**运营人员对象*/
	private CrmAdminInfo crmAdminInfo;
	/** 科室，对应 major_id */
	private Integer majorId;
	/** 科室对象 */
	private HospitalDoctorMajorInfo hospitalDoctorMajor;
	/**科室*/
	private String dmMajor;
	/**用户昵称*/
	private String nickName;
	/**用户联系电话*/
	private String userMobile;
	/**用户额外信息对象*/
	private UserExtraInfoBean userExtraInfo;
	/**用户真实姓名*/
	private String realName;
    /**服务的类型, 0:义诊1:图文,2:预约挂号,3:私人医生*/
    private String serviceType;
	/**医生姓名*/
	private String docName;
	/**咨询的内容*/
	private String content;
	/**音频文件或者图片文件*/
	private String fileUrl;
	/**问题状态：修改为：0：未认领；1：已认领;2：未回复；3：已回复；4：待处理；5：已结束；6：已拒绝（拒绝后问题废弃）;7:免费咨询 */
	private Integer status;
	/** 音频时长 */
	private Integer length;
	/** 0：免费咨询，1：图文咨询，2：私人医生，3：义诊（0,3都是免费的） */
	private Integer free;
	/** 购买记录id仅用户付费，免费则为null */
	private Integer payConsultantId;
	/**0:未评价；1：已经评价 */
	private Integer evaluate;
	/**对应的运营商（预留字段）（parter_appkey_info）的id */
	private Integer appkeyId;
	/**0：未删除；1：已删除 */
	private Integer isDelete;
	/**添加时间 */
	private Timestamp addTime;
	private String addTimes;
	/** 医院医生的回复 */
	private Set hospitalConsultantReplies = new HashSet(0);
	/** 是否已经转诊 */
	private Integer isReferral;
	/** 操作时间(修改，删除时更新) */
	private Timestamp updateTime;
	private String updateTimes;
	/** 认领时间 */
	private Timestamp cliemdTime;
	private String cliemdTimes;
	/** 提醒类型：0 : 未提醒; 1：10分钟提醒; 2 : 30分钟提醒 */
	private Integer alterType;
	/** 问题是否已处理 */
	private Integer isDeal;
	/** 合作商自带的用户ID */
	private Integer uId;
	/** 开始咨询时间 */
	private Timestamp startTime;
	private String startTimes;
	/** 关闭咨询时间 */
	private Timestamp closeTime;
	private String closeTimes;
	/**最后一次回复时间 */
	private Timestamp lastRepleyTime;
	private String lastRepleyTimes;
	/**免费咨询ID*/
	private String free_chartID;
	/**是否是运营人员，0：普通医生，1：运营人员*/
	private Integer isAdmin;
	// Constructors

	public String getStatusDisplay() {
		switch (null != status?status:-1) {
		case 0:
			return "未认领";
		case 1:
			return "已认领";
		case 2:
			return "未回复";
		case 3:
			return "已回复";
		case 4:
			return "待处理";
		case 5:
			return "已结束";
		case 6:
			return "已拒绝";
		case 7:
			return "免费咨询";
		default:
			return "";
		}
	}
	//修改为：0：未认领；1：已认领;2：未回复；3：已回复；4：待处理；5：已结束；6：已拒绝（拒绝后问题废弃）;7:免费咨询
	
	
	/** default constructor */
	public HospitalConsultantInfo() {
	}

	/** 创建一个新的实例 HospitalConsultantBean.*/
	public HospitalConsultantInfo(Integer userId, Integer doctorId, HospitalDoctorInfo hospitalDoctorInfo,
			Integer majorId, HospitalDoctorMajorInfo hospitalDoctorMajor, String content, String fileUrl,
			Integer status, Integer length, Integer free, Integer payConsultantId, Integer evaluate, Integer appkeyId,
			Integer isDelete, Timestamp addTime, Set hospitalConsultantReplies, Integer isReferral,
			Timestamp updateTime, Timestamp cliemdTime, Integer alterType, Integer isDeal, Timestamp startTime,
			Timestamp closeTime, Timestamp lastRepleyTime) {
		super();
		this.userId = userId;
		this.doctorId = doctorId;
		this.hospitalDoctorInfo = hospitalDoctorInfo;
		this.majorId = majorId;
		this.hospitalDoctorMajor = hospitalDoctorMajor;
		this.content = content;
		this.fileUrl = fileUrl;
		this.status = status;
		this.length = length;
		this.free = free;
		this.payConsultantId = payConsultantId;
		this.evaluate = evaluate;
		this.appkeyId = appkeyId;
		this.isDelete = isDelete;
		this.addTime = addTime;
		this.hospitalConsultantReplies = hospitalConsultantReplies;
		this.isReferral = isReferral;
		this.updateTime = updateTime;
		this.cliemdTime = cliemdTime;
		this.alterType = alterType;
		this.isDeal = isDeal;
		this.startTime = startTime;
		this.closeTime = closeTime;
		this.lastRepleyTime = lastRepleyTime;
	}

	/**创建一个新的实例 HospitalConsultantBean.*/
	public HospitalConsultantInfo(Integer userId, Integer doctorId, Integer majorId, String content, String fileUrl,
			Integer status, Integer length, Integer free, Integer payConsultantId, Integer evaluate, Integer appkeyId,
			Integer isDelete, Timestamp addTime, Set hospitalConsultantReplies, Integer isReferral,
			Timestamp updateTime, Timestamp cliemdTime) {
		super();
		this.userId = userId;
		this.doctorId = doctorId;
		this.majorId = majorId;
		this.content = content;
		this.fileUrl = fileUrl;
		this.status = status;
		this.length = length;
		this.free = free;
		this.payConsultantId = payConsultantId;
		this.evaluate = evaluate;
		this.appkeyId = appkeyId;
		this.isDelete = isDelete;
		this.addTime = addTime;
		this.hospitalConsultantReplies = hospitalConsultantReplies;
		this.isReferral = isReferral;
		this.updateTime = updateTime;
		this.cliemdTime = cliemdTime;
	}


	public String getAddTimes() {
		return addTimes;
	}


	public void setAddTimes(String addTimes) {
		this.addTimes = addTimes;
	}


	public String getUpdateTimes() {
		return updateTimes;
	}


	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
	}


	public String getCliemdTimes() {
		return cliemdTimes;
	}


	public void setCliemdTimes(String cliemdTimes) {
		this.cliemdTimes = cliemdTimes;
	}


	public String getStartTimes() {
		return startTimes;
	}


	public void setStartTimes(String startTimes) {
		this.startTimes = startTimes;
	}


	public String getCloseTimes() {
		return closeTimes;
	}


	public void setCloseTimes(String closeTimes) {
		this.closeTimes = closeTimes;
	}


	public String getLastRepleyTimes() {
		return lastRepleyTimes;
	}


	public void setLastRepleyTimes(String lastRepleyTimes) {
		this.lastRepleyTimes = lastRepleyTimes;
	}


	public CrmAdminInfo getCrmAdminInfo() {
		return crmAdminInfo;
	}


	public void setCrmAdminInfo(CrmAdminInfo crmAdminInfo) {
		this.crmAdminInfo = crmAdminInfo;
	}


	public Integer getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFree_chartID() {
		return free_chartID;
	}
	public void setFree_chartID(String free_chartID) {
		this.free_chartID = free_chartID;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public DoctorCommentsInfo getDoctorComments() {
		return doctorComments;
	}
	public void setDoctorComments(DoctorCommentsInfo doctorComments) {
		this.doctorComments = doctorComments;
	}
	public UserExtraInfoBean getUserExtraInfo() {
		return userExtraInfo;
	}
	public void setUserExtraInfo(UserExtraInfoBean userExtraInfo) {
		this.userExtraInfo = userExtraInfo;
	}
	public String getDmMajor() {
		return dmMajor;
	}
	public void setDmMajor(String dmMajor) {
		this.dmMajor = dmMajor;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public HospitalDoctorInfo getHospitalDoctorInfo() {
		return hospitalDoctorInfo;
	}
	public void setHospitalDoctorInfo(HospitalDoctorInfo hospitalDoctorInfo) {
		this.hospitalDoctorInfo = hospitalDoctorInfo;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	public HospitalDoctorMajorInfo getHospitalDoctorMajor() {
		return hospitalDoctorMajor;
	}
	public void setHospitalDoctorMajor(HospitalDoctorMajorInfo hospitalDoctorMajor) {
		this.hospitalDoctorMajor = hospitalDoctorMajor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getFree() {
		return free;
	}
	public void setFree(Integer free) {
		this.free = free;
	}
	public Integer getPayConsultantId() {
		return payConsultantId;
	}
	public void setPayConsultantId(Integer payConsultantId) {
		this.payConsultantId = payConsultantId;
	}
	public Integer getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}
	public Integer getAppkeyId() {
		return appkeyId;
	}
	public void setAppkeyId(Integer appkeyId) {
		this.appkeyId = appkeyId;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Set getHospitalConsultantReplies() {
		return hospitalConsultantReplies;
	}
	public void setHospitalConsultantReplies(Set hospitalConsultantReplies) {
		this.hospitalConsultantReplies = hospitalConsultantReplies;
	}
	public Integer getIsReferral() {
		return isReferral;
	}
	public void setIsReferral(Integer isReferral) {
		this.isReferral = isReferral;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Timestamp getCliemdTime() {
		return cliemdTime;
	}
	public void setCliemdTime(Timestamp cliemdTime) {
		this.cliemdTime = cliemdTime;
	}
	public Integer getAlterType() {
		return alterType;
	}
	public void setAlterType(Integer alterType) {
		this.alterType = alterType;
	}
	public Integer getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}
	public Timestamp getLastRepleyTime() {
		return lastRepleyTime;
	}
	public void setLastRepleyTime(Timestamp lastRepleyTime) {
		this.lastRepleyTime = lastRepleyTime;
	}
	/* (non-Javadoc)
	* @see java.lang.Object#toString()
	*/
	@Override
	public String toString() {
		return "HospitalConsultantInfo [id=" + id + ", userId=" + userId + ", doctorId=" + doctorId
				+ ", doctorComments=" + doctorComments + ", majorId=" + majorId + ", dmMajor=" + dmMajor + ", nickName="
				+ nickName + ", userMobile=" + userMobile + ", realName=" + realName + ", serviceType=" + serviceType
				+ ", docName=" + docName + ", content=" + content + ", fileUrl=" + fileUrl + ", status=" + status
				+ ", length=" + length + ", free=" + free + ", payConsultantId=" + payConsultantId + ", evaluate="
				+ evaluate + ", appkeyId=" + appkeyId + ", isDelete=" + isDelete + ", addTime=" + addTime
				+ ", hospitalConsultantReplies=" + hospitalConsultantReplies + ", isReferral=" + isReferral
				+ ", updateTime=" + updateTime + ", cliemdTime=" + cliemdTime + ", alterType=" + alterType + ", isDeal="
				+ isDeal + ", uId=" + uId + ", startTime=" + startTime + ", closeTime=" + closeTime
				+ ", lastRepleyTime=" + lastRepleyTime + ", free_chartID=" + free_chartID + "]";
	}


	// Property accessors

	

}
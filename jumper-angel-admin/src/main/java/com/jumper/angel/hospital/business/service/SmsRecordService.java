package com.jumper.angel.hospital.business.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.hospital.business.entity.SmsCharge;
import com.jumper.angel.hospital.business.entity.SmsContent;
import com.jumper.angel.hospital.business.entity.SmsRecord;
import com.jumper.angel.utils.ResultMsg;

/**
* @Description: 短信统计Service
* @author liump
* @date 2017年4月28日 下午2:56:03
* @Modifier:
* @modifydate:
*/
public interface SmsRecordService {

	/**
	 * 根据医院id获取医院发送短信的统计数据
	 * @param id 医院id
	 * @param hospName  医院名称
	 * @return
	 */
	List<SmsRecord> searchSmsRecordByHospId(Integer id, String hospName);

	/**
	 * 
	 * 根据医院名称获取医院列表及发送短信统计的分页数据
	 * @Title: findAllHospitalSmsStatisticsList
	 * @param: @param beginIndex
	 * @param: @param everyPage
	 * @param: @return
	 * @return: ResultMsg
	 */
	ResultMsg findAllHospitalSmsStatisticsList(int beginIndex, int everyPage);//

	/**
	 * 
	 * TODO(根据医院hospId获取医院各个业务发送短信的统计数据)
	 * @param everyPage 
	 * @param beginIndex 
	 * @Title: searchSmsDetailByHospID
	 * @param: @param hospId
	 * @param: @return
	 * @return: List<SmsRecord>
	 */
	List<SmsRecord> searchSmsDetailByHospID(String hospId, int beginIndex, int everyPage);//

	/**
	 * 
	 * TODO(根据医院ID查询该医院发送短信使用的业务渠道数量)
	 * @Title: findCount
	 * @param: @param hospId
	 * @param: @return
	 * @return: int
	 */
	int findCount(String hospId);//

	/**
	 * 
	 * TODO(根据医院ID，业务渠道ID等条件统计发送短信数量)
	 * @Title: getCount
	 * @param: @param hospId
	 * @param: @param appid
	 * @param: @param mobile
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @return
	 * @return: int
	 */
	int getCount(String hospId, String appid, String mobile, String beginTime, String endTime);//

	/**
	 * 
	 * TODO(根据医院ID，业务渠道ID等条件统计发送短信成功，失败数量)
	 * @Title: countHospitalStatistics
	 * @param: @param hospId
	 * @param: @param appid
	 * @param: @param mobile
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @return
	 * @return: Map<String,Object>
	 */
	Map<String, Object> countHospitalStatistics(String hospId, String appid, String mobile, String beginTime,
			String endTime);//

	/**
	 * 
	 * TODO(根据条件分页查询发送短信及手机号码信息)
	 * @Title: findUserDetailStatistics
	 * @param: @param beginIndex
	 * @param: @param everyPage
	 * @param: @param hospId
	 * @param: @param appid
	 * @param: @param mobile
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @return
	 * @return: List<SmsCharge>
	 */
	List<SmsCharge> findUserDetailStatistics(int beginIndex, int everyPage, String hospId, String appid, String mobile,
			String beginTime, String endTime);//

	/**
	 * 
	 * TODO(查询使用昊博短信平台的医院数量,参数可不传)
	 * @Title: useSmsCount
	 * @param: @return
	 * @return: int
	 */
	int useSmsCount(String hospName);//


	/**
	 * 根据医院名称获取医院列表及发送短信统计的分页数据
	 * @Title: findHospitalSmsStatisticsListByName
	 * @param: @param beginIndex
	 * @param: @param everyPage
	 * @param: @param hospName
	 * @param: @return
	 * @return: ResultMsg
	 */
	ResultMsg findHospitalSmsStatisticsListByName(int beginIndex, int everyPage, String hospName);//

}

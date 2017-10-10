package com.jumper.angel.hospital.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.business.entity.SmsCharge;
import com.jumper.angel.hospital.business.entity.SmsContent;
import com.jumper.angel.hospital.business.entity.SmsRecord;
import com.jumper.angel.hospital.business.service.SmsRecordService;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.mapper.HospitalInfoMapper;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.json.JsonUtils;

import net.sf.json.JSONArray;
/**
* @ClassName: SmsRecordServiceImpl
* @Description: 短信统计ServiceImpl
* @author liump
* @date 2017年4月28日 下午3:04:51
* @Modifier:
* @modifydate:
*/
@Service
public class SmsRecordServiceImpl implements SmsRecordService {

	private final static Logger logger = Logger.getLogger(SmsRecordServiceImpl.class);
	
	@Autowired
	private HospitalInfoMapper hospitalInfoMapper;
	

	/**
	 * 
	 * 获取所有使用昊博短信平台的医院短信统计分页数据
	 * @Title: findAllHospitalSmsStatisticsList
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	@Override
	public ResultMsg findAllHospitalSmsStatisticsList(int beginIndex, int everyPage) {//
		List<SmsRecord> returnList = new ArrayList<>();
			// 调用common   发送短信统计
			String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
			String sms_allHospSms = url+ "/sms/allHospSms?beginIndex="+beginIndex+"&everyPage="+everyPage;
			String returnVal = HttpPost.doPost(sms_allHospSms, "");
			logger.info("获取所有使用昊博短信平台的医院短信统计调用common的URL："+sms_allHospSms);
			HashMap<String, Object> reMap = JsonUtils.toHashMap(returnVal);
			Object data = reMap.get("data");
			if (data != null && "1".equals(reMap.get("msg").toString())){
				List<SmsRecord> reList = JsonUtils.toList(reMap.get("data"), SmsRecord.class);
				for(int i=0;i<reList.size();i++){
					returnList.add(reList.get(i));
				}
			}
			return new ResultMsg(Status.SUCCESS,"获取短信统计成功！",returnList);
	}
	
	@Override
	public ResultMsg findHospitalSmsStatisticsListByName(int beginIndex, int everyPage, String hospName) {//
		List<SmsRecord> returnList = new ArrayList<>();
		//根据医院名称获取医院id
		HospitalInfo hospitalInfo= hospitalInfoMapper.findHospitalInfoByName(hospName);
		if(hospitalInfo!=null){
			int id = hospitalInfo.getId();
			Integer hospId = new Integer(id);
			returnList = searchSmsRecordByHospId(hospId,hospName);
			if(returnList!=null&&returnList.size()>0){
				for (SmsRecord smsRecord : returnList) {
					smsRecord.setHospName(hospName);
				}
				return new ResultMsg(Status.SUCCESS,"获取短信统计成功！",returnList);
			}
		}
		return new ResultMsg(Status.FAILED,"获取短信统计失败！",returnList);
	}
	
	/**
	* @Title: searchSmsRecordByHospId
	* @Description: 根据医院hospId获取医院发送短信的统计数据
	* @author liump
	* @param:
	* @return:
	* @throws
	*/
	@Override
	public List<SmsRecord> searchSmsRecordByHospId(Integer hospId,String hospName) {//
		List<SmsRecord> list = new ArrayList<SmsRecord>();
		try {
			// 调用common   发送短信统计
			String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
			String sms_statistics_sel = url+ "/sms/sel_smsByHospId?hospId="+hospId;
			String returnVal = HttpPost.doPost(sms_statistics_sel, "");
			logger.info("短信统计调用common的URL："+sms_statistics_sel);
			HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
			Object data = map.get("data");
			System.out.println(data);
			if (data != null && "1".equals(map.get("msg").toString())){
			//	SmsRecord obj = (SmsRecord) data;
				SmsRecord obj = JsonUtils.toBean(data, SmsRecord.class);
				SmsRecord smsRecord = new SmsRecord();
				smsRecord.setHospId(Long.valueOf(hospId+""));
				smsRecord.setHospName(hospName);
				smsRecord.setTotalSend(obj.getTotalSend());
				smsRecord.setTotalSuccess(obj.getTotalSuccess());
				smsRecord.setTotalFailure(obj.getTotalFailure());
				list.add(smsRecord);
				return list;
			}
		} catch (Exception e) {
			logger.info("根据医院id获取短信统计 MSG = "+e.getMessage(), e);
		}
		return list;
	}
	

	/**
	 * 
	 * 根据医院hospId获取医院各个业务发送短信的统计数据
	 * @Title: searchSmsDetailByHospID
	 * @param hospId
	 * @return
	 */
	@Override
	public List<SmsRecord> searchSmsDetailByHospID(String hospId, int beginIndex, int everyPage) { //
		List<SmsRecord> list = new ArrayList<SmsRecord>();
		// 调用common   发送短信统计
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		String sms_statistics_detail = url+ "/sms/sel_smsDetailByHospId?hospId="+hospId+"&beginIndex="+beginIndex+"&everyPage="+everyPage;
		logger.info("根据医院hospId获取医院各个业务发送短信的统计数据的URL地址："+sms_statistics_detail);
		// 接口返回参数
		String returnVal = HttpPost.doPost(sms_statistics_detail, "");
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		Object data = map.get("data");
		if (data != null && "1".equals(map.get("msg").toString())){
			List<SmsRecord> reList = JsonUtils.toList(map.get("data"), SmsRecord.class);
			for(int i=0;i<reList.size();i++){
				list.add(reList.get(i));
			}
		}
		return list;
	}

	/**
	 * 
	 * TODO (根据医院ID查询该医院发送短信使用的业务渠道数量)
	 * @Title: findCount
	 * @param hospId
	 * @return
	 */
	@Override
	public int findCount(String hospId) {//
		// 调用common   发送短信统计
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		String sms_count = url+ "/sms/count?hospId="+hospId;
		logger.info("根据医院ID查询该医院发送短信使用的业务渠道数量的URL地址："+sms_count);
		String returnVal = HttpPost.doPost(sms_count, "");
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		Object data = (int) map.get("data");
		if (data != null && "1".equals(map.get("msg").toString())){
			int count = (int) data;
			return count;
		}
		return 0;
	}

	/**
	 * 
	 * TODO (根据医院ID，业务渠道ID等条件统计发送短信数量)
	 * @Title: getCount
	 * @param hospId
	 * @param appid
	 * @param mobile
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Override
	public int getCount(String hospId, String appid, String mobile, String beginTime, String endTime) {//
		// 调用common   发送短信统计
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		String sms_getCount = url+ "/sms/getCount?hospId="+hospId+"&appid="+appid+"&mobile="+mobile+"&beginTime="+beginTime+"&endTime="+endTime;
		logger.info("根据医院ID，业务渠道ID等条件统计发送短信数量的URL地址："+sms_getCount);
		String returnVal = HttpPost.doPost(sms_getCount, "");
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		Object data = (int) map.get("data");
		if (data != null && "1".equals(map.get("msg").toString())){
			int count = (int) data;
			return count;
		}
		return 0;
	}

	/**
	 * 
	 * TODO (根据医院ID，业务渠道ID等条件统计发送短信成功，失败数量)
	 * @Title: countHospitalStatistics
	 * @param hospId
	 * @param appid
	 * @param mobile
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Override
	public Map<String, Object> countHospitalStatistics(String hospId, String appid, String mobile, String beginTime,
			String endTime) {//
		HashMap<String, Object> reMap  = new HashMap<String, Object>();
		// 调用common   发送短信统计
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		String sms_countHospitalStatistics = url+ "/sms/countHospitalStatistics?hospId="+hospId+"&appid="+appid+"&mobile="+mobile+"&beginTime="+beginTime+"&endTime="+endTime;
		logger.info("根据医院ID，业务渠道ID等条件统计发送短信成功，失败数量的URL地址："+sms_countHospitalStatistics);
		String returnVal = HttpPost.doPost(sms_countHospitalStatistics, "");
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		Object data = map.get("data");
		if (data != null && "1".equals(map.get("msg").toString())){
			reMap = JsonUtils.toHashMap(map.get("data"));
			return reMap;
		}
		return reMap;
	}

	/**
	 * 
	 * TODO (根据条件分页查询发送短信及手机号码信息)
	 * @Title: findUserDetailStatistics
	 * @param beginIndex
	 * @param everyPage
	 * @param hospId
	 * @param appid
	 * @param mobile
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Override
	public List<SmsCharge> findUserDetailStatistics(int beginIndex, int everyPage, String hospId, String appid,
			String mobile, String beginTime, String endTime) {//
		List<SmsCharge> list = new ArrayList<SmsCharge>();
		// 调用common   发送短信统计
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		String sms_statistics_userDetail = url+ "/sms/findUserDetailStatistics?hospId="+hospId+"&appid="+appid+"&mobile="+mobile+
				"&beginTime="+beginTime+"&endTime="+endTime+"&beginIndex="+beginIndex+"&everyPage="+everyPage;
		logger.info("根据条件分页查询发送短信及手机号码信息的URL地址："+sms_statistics_userDetail);
		// 接口返回参数
		String returnVal = HttpPost.doPost(sms_statistics_userDetail, "");
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		Object data = map.get("data");
		if (data != null && "1".equals(map.get("msg").toString())){
			List<SmsCharge> reList = JsonUtils.toList(map.get("data"), SmsCharge.class);
			for(int i=0;i<reList.size();i++){
				list.add(reList.get(i));
			}
		}
		return list;
	}

	/**
	 * 
	 * TODO (查询使用昊博短信平台的医院数量,参数可不传)
	 * @Title: useSmsCount
	 * @return
	 */
	@Override
	public int useSmsCount(String hospName) {//
		String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
		if(hospName!=null&&!"".equals(hospName)){//传入了医院名称
			//根据医院名称获取医院id
			HospitalInfo hospitalInfo= hospitalInfoMapper.findHospitalInfoByName(hospName);
			if(hospitalInfo!=null){
				int hospId = hospitalInfo.getId();
				// 调用common   根据医院ID查询使用昊博短信平台的医院数量
				String sms_useSmsCountById = url+ "/sms/useSmsCountById?hospId="+hospId;
				logger.info("查询使用昊博短信平台的医院数量的URL地址："+sms_useSmsCountById);
				// 接口返回参数
				String returnVal = HttpPost.doPost(sms_useSmsCountById, "");
				HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
				Object data = map.get("data");
				if (data != null && "1".equals(map.get("msg").toString())){
					int count = (int) data;
					return count;
				}
			}
		}else{//没有传入医院名称
			// 调用common   根据医院ID查询使用昊博短信平台的医院数量
	//		int hospId = 0;
			String sms_allUseSmsCount = url+ "/sms/allUseSmsCount";
			logger.info("查询使用昊博短信平台的医院数量的URL地址："+sms_allUseSmsCount);
			// 接口返回参数
			String returnVal = HttpPost.doPost(sms_allUseSmsCount, "");
			HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
			Object data = map.get("data");
			if (data != null && "1".equals(map.get("msg").toString())){
				int count = (int) data;
				return count;
			}
		}
		return 0;
	}

}

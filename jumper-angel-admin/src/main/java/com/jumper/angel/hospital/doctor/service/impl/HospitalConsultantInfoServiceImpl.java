package com.jumper.angel.hospital.doctor.service.impl;

import java.sql.Timestamp;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.doctor.entity.DoctorCommentsInfo;
import com.jumper.angel.hospital.doctor.entity.DoctorOrderInfo;
import com.jumper.angel.hospital.doctor.entity.HospitalConsultantInfo;
import com.jumper.angel.hospital.doctor.entity.HospitalDoctorInfo;
import com.jumper.angel.hospital.doctor.entity.HospitalDoctorMajorInfo;
import com.jumper.angel.hospital.doctor.mapper.DoctorCommentsInfoMapper;
import com.jumper.angel.hospital.doctor.mapper.DoctorOrderInfoMapper;
import com.jumper.angel.hospital.doctor.mapper.HospitalConsultantInfoMapper;
import com.jumper.angel.hospital.doctor.mapper.HospitalDoctorInfoMapper;
import com.jumper.angel.hospital.doctor.mapper.HospitalDoctorMajorInfoMapper;
import com.jumper.angel.hospital.doctor.service.IHospitalConsultantInfoService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.PhoneUtil;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;
@Service
public class HospitalConsultantInfoServiceImpl implements IHospitalConsultantInfoService {
	private final static Logger logger = Logger.getLogger(HospitalConsultantInfo.class);
	@Autowired
	private HospitalConsultantInfoMapper hospitalConsultantInfoMapper;
	@Autowired
	private HospitalDoctorMajorInfoMapper hospitalDoctorMajorInfoMapper;
	@Autowired
	private HospitalDoctorInfoMapper hospitalDoctorInfoMapper;
//	@Autowired
//	private UserInfoBeanMapper userInfoBeanMapper;
//	@Autowired
//	private UserExtraInfoBeanMapper userExtraInfoBeanMapper;
	@Autowired
	private DoctorCommentsInfoMapper doctorCommentsInfoMapper;
	@Autowired
	private DoctorOrderInfoMapper doctorOrderInfoMapper;
//	@Autowired
//	private CrmAdminInfoMapper crmAdminInfoMapper;
	
	
	/**通过问题ID查询该问题*/
	@Override
	public HospitalConsultantInfo findConsultantInfoById(Integer consultantId) {
		return hospitalConsultantInfoMapper.selectByPrimaryKey(consultantId);
	}

	/**查询所有咨询信息的数量*/
	public int findConsultantCount(String keyword) {
		return hospitalConsultantInfoMapper.findConsultantCount(keyword);
	}
	
	/**查找所有咨询信息并分页*/
	public List<Map<String, Object>> findAllHospitalConsultantInfo(int beginIndex, int everyPage,String keyword) {
		int start = 0;
		if (beginIndex>0) {
			start = (beginIndex-1)*everyPage;
		}
		List<Map<String, Object>> retList = new ArrayList<>();
		//查询所有科室信息
		List<HospitalDoctorMajorInfo> doctorMajorInfos = hospitalDoctorMajorInfoMapper.findAll();
		//查询所有的评论信息
		List<DoctorCommentsInfo> doctorCommentsInfos = doctorCommentsInfoMapper.findAll();
		//查询所有的医生信息
		List<HospitalDoctorInfo> doctorInfos = hospitalDoctorInfoMapper.findAll();
		//查询所有的订单信息
		List<DoctorOrderInfo> orderInfos = doctorOrderInfoMapper.findAll();
		//查询所有满足条件的信息
		List<Map<String, Object>> returnList = hospitalConsultantInfoMapper.findAllHospitalConsultantInfo(start,everyPage,keyword);
		for (Map<String, Object> paramMap : returnList) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			String id = paramMap.get("id").toString();
			retMap.put("id", id);
			retMap.put("content", paramMap.get("content")!=null?paramMap.get("content"):"");
			String nick_name = "";
			if (paramMap.get("nick_name")!=null) {
				nick_name = paramMap.get("nick_name").toString();
			}
			if (nick_name!=null && !"".equals(nick_name)) {
				retMap.put("nick_name", nick_name);
			}else {
				String mobile = paramMap.get("mobile")!=null?paramMap.get("mobile").toString():"";
				if (mobile!=null && !"".equals(mobile)) {
					retMap.put("nick_name", PhoneUtil.format4(mobile));
				}else {
					retMap.put("nick_name", "【匿名用户】");
				}
			}
			retMap.put("real_name", paramMap.get("real_name")!=null?paramMap.get("real_name"):"");
			retMap.put("add_time", paramMap.get("add_time")!=null?TimeUtils.getTimeStampNumberFormat((Timestamp)paramMap.get("add_time")):"");
			retMap.put("user_id", paramMap.get("user_id")!=null?paramMap.get("user_id"):"0");
			Integer is_admin = paramMap.get("is_admin")!=null?Integer.valueOf(paramMap.get("is_admin").toString()):0;
			Integer doctor_id = paramMap.get("doctor_id")!=null?(Integer)paramMap.get("doctor_id"):0;
			retMap.put("is_admin", is_admin);
			retMap.put("doctor_id", doctor_id);
			if (doctor_id!=0) {
				/**通过doctor_id查询咨询医生*/
				if (is_admin==1) {
					retMap.put("doc_name", "【天使医生】");
					retMap.put("service_type", "-1");
				}else {
					for (HospitalDoctorInfo doctorInfo : doctorInfos) {
						if (doctor_id.equals(doctorInfo.getId())) {
							retMap.put("doc_name", doctorInfo.getName());
							break;
						}else {
							retMap.put("doc_name", "");
						}
					}
					for (DoctorOrderInfo orderInfo : orderInfos) {
						if (id.equals(orderInfo.getConsultantId())) {
							retMap.put("service_type", orderInfo.getSendType());
							break;
						}else {
							retMap.put("service_type", -1);
						}
					}
				}
			}else {
				retMap.put("doc_name", "");
				retMap.put("service_type", -1);
			}
			String major_id = paramMap.get("major_id").toString();
			if (major_id!=null&&!"".equals(major_id)) {
				String major_name = "暂无该科室";
				for (HospitalDoctorMajorInfo majorInfo : doctorMajorInfos) {
					if (major_id.equals(majorInfo.getId().toString())) {
						major_name = majorInfo.getMajor();
						break;
					}
				}
				retMap.put("major_name", major_name);
			}
			String evaluate = paramMap.get("evaluate")!=null?paramMap.get("evaluate").toString():"0";
			retMap.put("evaluate", evaluate);
			if ("1".equals(evaluate)) {
				for (DoctorCommentsInfo commentsInfo : doctorCommentsInfos) {
					if (commentsInfo.getConstantId().toString().equals(id)) {
						retMap.put("comments_content", commentsInfo.getContent());
						retMap.put("comments_status", commentsInfo.getStatus());
						break;
					}else {
						retMap.put("comments_content", "");
						retMap.put("comments_status", 1);
					}
				}
			}else {
				retMap.put("comments_content", "");
				retMap.put("comments_status", 0);
			}
			String status = paramMap.get("status")!=null?paramMap.get("status").toString():"-1";
			String statusDisplay = getStatusDisplay(Integer.valueOf(status));
			retMap.put("statusDisplay", statusDisplay);
			retList.add(retMap);
		}
		return retList;
	}
	public String getStatusDisplay(Integer status) {
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
			return "状态异常";
		}
	}
	
	/**查询问题库的数量*/
	public int findProblemBaseCount(String keyword) {
		return hospitalConsultantInfoMapper.findProblemBaseCount(keyword);
	}
	
	/**查找所有问题库问题并分页*/
	public List<Map<String, Object>> findAllProblemBase(int beginIndex, int everyPage,String keyword) {
		int start = 0;
		if (beginIndex>0) {
			start = (beginIndex-1)*everyPage;
		}
		//查询所有科室信息
		List<HospitalDoctorMajorInfo> doctorMajorInfos = hospitalDoctorMajorInfoMapper.findAll();
		
		List<Map<String, Object>> returnList = hospitalConsultantInfoMapper.findAllProblemBase(start,everyPage,keyword);
		for (Map<String, Object> map : returnList) {
			String major_id = map.get("major_id").toString();
			if (major_id!=null&&!"".equals(major_id)) {
				String major_name = "暂无该科室";
				for (HospitalDoctorMajorInfo majorInfo : doctorMajorInfos) {
					if (major_id.equals(majorInfo.getId().toString())) {
						major_name = majorInfo.getMajor();
						break;
					}
				}
				map.put("major_name", major_name);
			}
			map.put("add_time", map.get("add_time")!=null?TimeUtils.getTimeStampNumberFormat((Timestamp)map.get("add_time")):"");
			map.put("real_name", map.get("real_name")!=null?map.get("real_name"):"");
			String nick_name = "";
			if (map.get("nick_name")!=null) {
				nick_name = map.get("nick_name").toString();
			}
			if (nick_name!=null && !"".equals(nick_name)) {
				map.put("nick_name", nick_name);
			}else {
				String mobile = map.get("mobile")!=null?map.get("mobile").toString():"";
				if (mobile!=null && !"".equals(mobile)) {
					map.put("nick_name", PhoneUtil.format4(mobile));
				}else {
					map.put("nick_name", "【匿名用户】");
				}
			}
		}
		return returnList;
	}

	/**领取问题*/
	public HospitalConsultantInfo getProblem(Integer commentId,Integer currentId, String busCode) {
		try {
			//查询当前对象
			HospitalConsultantInfo hospitalConsultantInfo = hospitalConsultantInfoMapper.selectByPrimaryKey(commentId);
			if (hospitalConsultantInfo.getStatus()==0) {
				
				String ys_chat_id = addChatId(currentId+"", ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("CRM_OPENID")+currentId);
				String yf_chat_id = addChatId(hospitalConsultantInfo.getUserId()+"", ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("USER_OPENID")+hospitalConsultantInfo.getUserId());
//				String yf_chat_id = hospitalConsultantInfoMapper.selectYFChatId(hospitalConsultantInfo.getUserId());
				if (ys_chat_id.isEmpty()) {
					throw new Exception("获取医生chatID失败!");
				}
				if (yf_chat_id.isEmpty()) {
					throw new Exception("获取用户chatID失败!");
				}
				int msg = updateDoctorQuestion(yf_chat_id, ys_chat_id, commentId+"");
				logger.info("++++++++++++++++++++++msg="+msg);
				if (msg==1) {
					Timestamp time = TimeUtils.getCurrentTime();
					//改变status为2，未回复
					hospitalConsultantInfo.setStatus(2);
					hospitalConsultantInfo.setIsAdmin(1);
					hospitalConsultantInfo.setDoctorId(currentId);
					hospitalConsultantInfo.setUpdateTime(time);
					hospitalConsultantInfo.setStartTime(time);
					hospitalConsultantInfo.setCloseTime(new Timestamp(TimeUtils.getCurrentTimeByDay(2, time).getTime()));
					logger.info("问题领取成功！");
					hospitalConsultantInfoMapper.updateByPrimaryKey(hospitalConsultantInfo);
				}else {
					logger.info("问题领取失败！");
				}
//				hospitalConsultantInfoMapper.updateIMByPrimaryKey(ys_chat_id,busCode,commentId,yf_chat_id,free_chartID);
	//			hospitalConsultantInfoMapper.updateIMByPrimaryKey(recevrer_chat_id,bus_code,consultant_id,send_chat_id,recevrer_chat_id);
			
			}
			return hospitalConsultantInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return new HospitalConsultantInfo();
		}
	}
	/** 修改用户提出问题的接收者IM帐号 */
	private int updateDoctorQuestion(String sender,String recevrer,String consultantId) {
		try {
			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + "/im/update_doctor_question";
			String postUrl = path+"?busCode=10154&sender="+sender+"&recevrer="+recevrer+"&consultantId="+consultantId;
			System.out.println("postUrl="+postUrl);
			String val = HttpPost.doPost(postUrl, "");
			HashMap<String, Object> param = JsonUtils.toHashMap(val);
			System.out.println("param="+param);
			int msg =  (int)param.get("msg");
			return msg;
		} catch (Exception e) {
			logger.error("修改用户提出问题的接收者IM帐号失败！",e);
			return 0;
		}
	}
	/**获取chatID*/
	private String addChatId(String userId,String appid,String openid) {
		String chatId = "";
		try {
			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + "/accounts/add";
			String postUrl = path+"?userId="+userId+"&appid="+appid+"&openid="+openid;
			System.out.println("postUrl="+postUrl);
			String val = HttpPost.doPost(postUrl, "");
			HashMap<String, Object> param = JsonUtils.toHashMap(val);
			String msg =  param.get("msg").toString();
			if ("1".equals(msg)){
				HashMap<String, Object> dataMap = JsonUtils.toHashMap(param.get("data"));
				chatId = dataMap.get("chatId").toString();
			}
		} catch (Exception e) {
			logger.error("获取chatID失败！");
			e.printStackTrace();
		}
		return chatId;
	}
	
	/**通过当前用户的id查询所有我的解答，回复中*/
	public List<Map<String, Object>> findMyAnswering(int beginIndex, int everyPage, String keyword, Integer currentId) {
		int start = 0;
		if (beginIndex>0) {
			start = (beginIndex-1)*everyPage;
		}
		//查询所有科室信息
		List<HospitalDoctorMajorInfo> doctorMajorInfos = hospitalDoctorMajorInfoMapper.findAll();
		
		List<Map<String, Object>> returnList = hospitalConsultantInfoMapper.selectByDoctorIdMyAnswering(start,everyPage,keyword,currentId);
		for (Map<String, Object> map : returnList) {
			String major_id = map.get("major_id").toString();
			if (major_id!=null&&!"".equals(major_id)) {
				String major_name = "暂无该科室";
				for (HospitalDoctorMajorInfo majorInfo : doctorMajorInfos) {
					if (major_id.equals(majorInfo.getId().toString())) {
						major_name = majorInfo.getMajor();
						break;
					}
				}
				map.put("major_name", major_name);
			}
			map.put("add_time", map.get("add_time")!=null?TimeUtils.getTimeStampNumberFormat((Timestamp)map.get("add_time")):"");
			map.put("real_name", map.get("real_name")!=null?map.get("real_name"):"");
			String nick_name = "";
			if (map.get("nick_name")!=null) {
				nick_name = map.get("nick_name").toString();
			}
			if (nick_name!=null && !"".equals(nick_name)) {
				map.put("nick_name", nick_name);
			}else {
				String mobile = map.get("mobile")!=null?map.get("mobile").toString():"";
				if (mobile!=null && !"".equals(mobile)) {
					map.put("nick_name", PhoneUtil.format4(mobile));
				}else {
					map.put("nick_name", "【匿名用户】");
				}
			}
			String status = map.get("status")!=null?map.get("status").toString():"-1";
			String statusDisplay = getStatusDisplay(Integer.valueOf(status));
			map.put("statusDisplay", statusDisplay);
		}
		return returnList;
	}
	/**通过当前用户的id查询所有我的解答，已结束*/
	public List<Map<String, Object>> findMyAnswerend(int beginIndex, int everyPage, String keyword, Integer currentId) {
		int start = 0;
		if (beginIndex>0) {
			start = (beginIndex-1)*everyPage;
		}
		//查询所有科室信息
		List<HospitalDoctorMajorInfo> doctorMajorInfos = hospitalDoctorMajorInfoMapper.findAll();
		
		List<Map<String, Object>> returnList = hospitalConsultantInfoMapper.selectByDoctorIdMyAnswerend(start,everyPage,keyword,currentId);
		for (Map<String, Object> map : returnList) {
			String major_id = map.get("major_id").toString();
			if (major_id!=null&&!"".equals(major_id)) {
				String major_name = "暂无该科室";
				for (HospitalDoctorMajorInfo majorInfo : doctorMajorInfos) {
					if (major_id.equals(majorInfo.getId().toString())) {
						major_name = majorInfo.getMajor();
						break;
					}
				}
				map.put("major_name", major_name);
			}
			map.put("add_time", map.get("add_time")!=null?TimeUtils.getTimeStampNumberFormat((Timestamp)map.get("add_time")):"");
			map.put("real_name", map.get("real_name")!=null?map.get("real_name"):"");
			String nick_name = "";
			if (map.get("nick_name")!=null) {
				nick_name = map.get("nick_name").toString();
			}
			if (nick_name!=null && !"".equals(nick_name)) {
				map.put("nick_name", nick_name);
			}else {
				String mobile = map.get("mobile")!=null?map.get("mobile").toString():"";
				if (mobile!=null && !"".equals(mobile)) {
					map.put("nick_name", PhoneUtil.format4(mobile));
				}else {
					map.put("nick_name", "【匿名用户】");
				}
			}
			String evaluate = map.get("evaluate")!=null?map.get("evaluate").toString():"-1";
			if (evaluate.equals("1")) {
				map.put("evaluate", "已评价");
			}else {
				map.put("evaluate", "未评价");
			}
		}
		return returnList;
	}
	
	/**查询我的解答的数量,回复中*/
	public int findMyAnsweringCount(String keyword, Integer currentId) {
		return hospitalConsultantInfoMapper.findMyAnsweringCount(keyword, currentId);
	}
	/**查询我的解答的数量,已结束*/
	public int findMyAnswerendCount(String keyword, Integer currentId) {
		return hospitalConsultantInfoMapper.findMyAnswerendCount(keyword, currentId);
	}
	
	/**通过consultId关闭咨询*/
	public int deleteConsult(Integer consultId) {
		Timestamp time = TimeUtils.getCurrentTime();
		//通过consultID查询该对象
		HospitalConsultantInfo hospitalConsultantInfo = hospitalConsultantInfoMapper.selectByPrimaryKey(consultId);
		hospitalConsultantInfo.setStatus(5);
		hospitalConsultantInfo.setUpdateTime(time);
		hospitalConsultantInfo.setCloseTime(time);
		
		int count = hospitalConsultantInfoMapper.updateByPrimaryKey(hospitalConsultantInfo);
		return count;
	}

	/**通过当前医生ID查询serviceType*/
	public Integer findServiceTypeByDoctorId(Integer currentId) {
		DoctorOrderInfo doctorOrderInfo = doctorOrderInfoMapper.selectByPrimaryKey(currentId);
		Integer serviceType = doctorOrderInfo.getSendType();
		return serviceType;
	}

	/** 修改咨询服务状态 */
	@Override
	public void updateStatusById(String id) {
		hospitalConsultantInfoMapper.updateStatusById(id);
	}

}

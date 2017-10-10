//package com.jumper.angel.hospital.doctor.service.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.httpclient.util.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jumper.angel.hospital.doctor.entity.HospitalDoctorInfoInfo;
//import com.jumper.angel.hospital.doctor.entity.IMMessage;
//import com.jumper.angel.hospital.doctor.entity.UserExtraInfoBean;
//import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
//import com.jumper.angel.hospital.doctor.mapper.HospitalConsultantInfoMapper;
//import com.jumper.angel.hospital.doctor.mapper.HospitalDoctorInfoInfoMapper;
//import com.jumper.angel.hospital.doctor.mapper.IMMessageMapper;
//import com.jumper.angel.hospital.doctor.mapper.UserExtraInfoBeanMapper;
//import com.jumper.angel.hospital.doctor.mapper.UserInfoBeanMapper;
//import com.jumper.angel.hospital.doctor.service.IIMMessageService;
//import com.jumper.angel.utils.Page;
//import com.jumper.angel.utils.StringUtil;
//@Service
//public class IMMessageServiceImpl implements IIMMessageService {
//
//	@Autowired
//	private HospitalConsultantInfoMapper hospitalConsultantInfoMapper;
//	@Autowired
//	private IMMessageMapper IMMessageMapper;
//	@Autowired
//	private HospitalDoctorInfoInfoMapper doctorInfoInfoMapper;
//	@Autowired
//	private UserInfoBeanMapper userInfoBeanMapper;
//	@Autowired
//	private UserExtraInfoBeanMapper userExtraInfoBeanMapper;
//	
//	/**查询咨询信息的总条数*/
//	public int findMessageCount(Map<String, Object> paramMap) {
//		Integer serviceType = (Integer) paramMap.get("serviceType");
//		Integer userId = (Integer) paramMap.get("userId");
//		Integer doctorId = (Integer) paramMap.get("doctorId");
//		String busCode = null;
//		/**服务的类型,0:义诊10153,1:图文咨询10150,2:医院问诊10152,3:胎心监护,4:在线问诊,5:私人医生10151,6:网络诊室,7:设备租赁,8:胎心解读*/
//		if (serviceType==0) {busCode = "10153";	}
//		if (serviceType==1) {busCode = "10150";	}
//		if (serviceType==2) {busCode = "10152";	}
//		if (serviceType==5) {busCode = "10151";	}
//		String sender = "yf_"+userId;
//		String  recevrer = "ys_"+doctorId;
//		return IMMessageMapper.findMessageCount(busCode,sender,recevrer,null,null);
//	}
//
//	public List<Map<String, Object>> findMessage(Map<String, Object> paramMap) {
//		try {
//			Integer serviceType = (Integer) paramMap.get("serviceType");
//			Integer userId = (Integer) paramMap.get("userId");
//			Integer doctorId = (Integer) paramMap.get("doctorId");
//			HospitalDoctorInfoInfo doctorInfoInfo = doctorInfoInfoMapper.selectByPrimaryKey(doctorId);
//			String ys_name = doctorInfoInfo.getName();
//			UserInfoBean userInfo = userInfoBeanMapper.selectByPrimaryKey(userId);
//			String yf_name = userInfo.getNickName();
//			UserExtraInfoBean userExtraInfo = userExtraInfoBeanMapper.selectByPrimaryKey(userId);
//			String yf_phone = userExtraInfo.getContactPhone();
//			String yf_phones = "*";
//			if (StringUtil.isEmpty(yf_phone)) {
//				yf_phones = "匿名";
//			}else {
//				char[] yf_phoneChar = yf_phone.toCharArray();
//				for (int index = yf_phoneChar.length-4; index < yf_phoneChar.length; index++) {
//					yf_phones += yf_phoneChar[index];
//				}
//			}
//			String busCode = null;
//			/**服务的类型,0:义诊10153,1:图文咨询10150,2:医院问诊10152,3:胎心监护,4:在线问诊,5:私人医生10151,6:网络诊室,7:设备租赁,8:胎心解读*/
//			if (serviceType==0) {busCode = "10153";	}
//			if (serviceType==1) {busCode = "10150";	}
//			if (serviceType==2) {busCode = "10152";	}
//			if (serviceType==5) {busCode = "10151";	}
////			String sender = "yf_"+userId;
////			String  recevrer = "ys_"+doctorId;
//			String sender = hospitalConsultantInfoMapper.selectYSChatId(doctorId);
//			String recevrer = hospitalConsultantInfoMapper.selectYFChatId(userId);
//			Page page = (Page) paramMap.get("page");
//			Integer currentPage = page.getCurrentPage();
//			Integer pageSize = page.getEveryPage();
//			if (pageSize==null||pageSize<=0) {
//				pageSize = 15;
//			}
//			if (currentPage==null||currentPage<=0) {
//				currentPage = 1;
//			}
//			Integer start = (currentPage-1)*pageSize;
//			List<IMMessage> imMessages = IMMessageMapper.findMessage(start,pageSize,busCode, sender, recevrer, null, null);
//			List<Map<String, Object>> list = new ArrayList<>();
//			for (IMMessage imMessage : imMessages) {
//				Map<String, Object> map = new HashMap<>();
//				if (imMessage.getSendChatId().contains("ys_")) {
//					imMessage.setSendChatName(ys_name);
//				}
//				if (imMessage.getSendChatId().contains("yf_")) {
//					if (!StringUtil.isEmpty(yf_name)) {
//						imMessage.setSendChatName(yf_name);
//					}else {
//						imMessage.setSendChatName(yf_phones);
//					}
//				}
//				System.out.println(imMessage.getSendTime());
//				map.put("sendChatName", imMessage.getSendChatName());
//				map.put("sendTime", imMessage.getSendTime());
//				map.put("msgContent", imMessage.getMsgContent());
//				list.add(map);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ArrayList<>();
//	}
//
//}
package com.jumper.angel.hospital.doctor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.doctor.entity.ImMsgPo;
import com.jumper.angel.hospital.doctor.mapper.ImMsgPoMapper;
import com.jumper.angel.hospital.doctor.service.IImMsgPoService;
import com.jumper.angel.utils.Page;
@Service
public class ImMsgPoServiceImpl implements IImMsgPoService {

	@Autowired
	private ImMsgPoMapper imMsgPoMapper;
	
	/**查询咨询信息的总条数*/
	public int findMsgCount(String busCode, String sender, String recevrer,String startTime, String endTime) {
		
		return imMsgPoMapper.findMsgCount(busCode,sender,recevrer,null,null);
	}

	public List<ImMsgPo> findMsg(Page page, String busCode, String sender, String recevrer, String startTime,
			String endTime,Integer consultantId) {
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getEveryPage();
		if (pageSize==null||pageSize<=0) {
			pageSize = 100;
		}
		if (currentPage==null||currentPage<=0) {
			currentPage = 1;
		}
		Integer start = (currentPage-1)*pageSize;
		List<ImMsgPo> imMsgs = imMsgPoMapper.findMsge(start,pageSize,busCode, sender, recevrer, null, null,consultantId);
		return imMsgs;
	}

}

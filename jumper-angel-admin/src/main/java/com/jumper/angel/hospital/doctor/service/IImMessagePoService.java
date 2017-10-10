package com.jumper.angel.hospital.doctor.service;

import java.util.List;

import com.jumper.angel.hospital.doctor.entity.ImMessagePo;
import com.jumper.angel.utils.Page;

/**消息记录对象相关服务*/
public interface IImMessagePoService {

	/**查询咨询信息的总条数*/
	int findMessageCount(String busCode, String sender, String recevrer, String startTime, String endTime);
//	int findMessageCount(Map<String, Object> paramMap);
//
//	/**查询咨询信息*/
//	List<Map<String, Object>> findMessage(Map<String, Object> paramMap);

	List<ImMessagePo> findMessage(Page page, String busCode, String sender, String recevrer, String startTime,
			String endTime,Integer consultantId);
}

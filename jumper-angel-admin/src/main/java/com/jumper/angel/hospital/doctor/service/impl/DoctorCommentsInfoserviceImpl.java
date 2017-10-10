package com.jumper.angel.hospital.doctor.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.doctor.entity.DoctorCommentsInfo;
import com.jumper.angel.hospital.doctor.mapper.DoctorCommentsInfoMapper;
import com.jumper.angel.hospital.doctor.service.IDoctorCommentsInfoService;
@Service
public class DoctorCommentsInfoserviceImpl implements IDoctorCommentsInfoService {

	@Autowired
	private DoctorCommentsInfoMapper doctorCommentsInfoMapper;
	
	
	public int deletComment(Integer consultantId) {
		/**通过commentId查询该对象*/
		DoctorCommentsInfo doctorCommentsInfo = doctorCommentsInfoMapper.selectByConsultantId(consultantId);
		doctorCommentsInfo.setAddTime(new Date());
		/**设置关闭评论*/
		doctorCommentsInfo.setStatus(DoctorCommentsInfo.CLOES_STATUS);
		int key = doctorCommentsInfoMapper.updateByPrimaryKey(doctorCommentsInfo);
		return key;
	}

}

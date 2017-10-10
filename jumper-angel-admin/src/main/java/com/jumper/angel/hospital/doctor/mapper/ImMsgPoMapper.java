package com.jumper.angel.hospital.doctor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.ImMsgPo;

public interface ImMsgPoMapper {
    
    /**查询咨询信息的总条数*/
	int findMsgCount(@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes);

	/**查询咨询信息*/
	List<ImMsgPo> findMsge(@Param("start")Integer start,@Param("pageSize") Integer pageSize, 
			@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes,@Param("consultantId")Integer consultantId);
}
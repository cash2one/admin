package com.jumper.angel.hospital.doctor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.ImMessagePo;

public interface ImMessagePoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImMessagePo record);

    int insertSelective(ImMessagePo record);

    ImMessagePo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImMessagePo record);

    int updateByPrimaryKey(ImMessagePo record);
    
    /**查询咨询信息的总条数*/
	int findMessageCount(@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes);

	/**查询咨询信息*/
	List<ImMessagePo> findMessage(@Param("start")Integer start,@Param("pageSize") Integer pageSize, 
			@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes,@Param("consultantId")Integer consultantId);
}
package com.jumper.angel.hospital.doctor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.IMMessage;
/**咨询信息记录相关mapper*/
public interface IMMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IMMessage record);

    int insertSelective(IMMessage record);

    IMMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IMMessage record);

    int updateByPrimaryKey(IMMessage record);
    
    /**查询咨询信息的总条数*/
	int findMessageCount(@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes);

	/**查询咨询信息*/
	List<IMMessage> findMessage(@Param("start")Integer start,@Param("pageSize") Integer pageSize, 
			@Param("busCode")String busCode,@Param("sender") String sender,
			@Param("recevrer") String recevrer,@Param("startTime") Date startTimes,
			@Param("endTime") Date endTimes,@Param("consultantId")Integer consultantId);
}
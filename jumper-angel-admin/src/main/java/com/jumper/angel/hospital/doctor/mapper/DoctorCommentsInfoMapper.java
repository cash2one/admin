package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.DoctorCommentsInfo;
/**评论相关Mapper*/
public interface DoctorCommentsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DoctorCommentsInfo record);

    int insertSelective(DoctorCommentsInfo record);

    DoctorCommentsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DoctorCommentsInfo record);

    int updateByPrimaryKey(DoctorCommentsInfo record);
    /**通过constantId查询对应的对象*/
    DoctorCommentsInfo selectByConstantId(@Param("constantId")Integer constantId);

	DoctorCommentsInfo selectByConsultantId(@Param("consultantId")Integer consultantId);

	List<DoctorCommentsInfo> findAll();
}
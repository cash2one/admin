package com.jumper.angel.hospital.hospital.mapper;

import java.util.List;

import com.jumper.angel.hospital.hospital.entity.HospitalMajorInfo;

public interface HospitalMajorInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalMajorInfo record);

    int insertSelective(HospitalMajorInfo record);

    HospitalMajorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalMajorInfo record);

    int updateByPrimaryKey(HospitalMajorInfo record);

	List<HospitalMajorInfo> selectByHospitalId(Integer id);
}
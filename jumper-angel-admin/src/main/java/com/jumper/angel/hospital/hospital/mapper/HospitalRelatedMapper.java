package com.jumper.angel.hospital.hospital.mapper;

import com.jumper.angel.hospital.hospital.entity.HospitalRelated;

public interface HospitalRelatedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalRelated record);

    int insertSelective(HospitalRelated record);

    HospitalRelated selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalRelated record);

    int updateByPrimaryKey(HospitalRelated record);

	HospitalRelated selectByHospId(Integer id);
}
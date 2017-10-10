package com.jumper.angel.hospital.hospital.mapper;

import java.util.List;

import com.jumper.angel.hospital.hospital.entity.HospitalModuleHost;

public interface HospitalModuleHostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalModuleHost record);

    int insertSelective(HospitalModuleHost record);

    HospitalModuleHost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalModuleHost record);

    int updateByPrimaryKey(HospitalModuleHost record);

    List<HospitalModuleHost> selectByHospitalId(Integer hospitalId);

	List<HospitalModuleHost> selectByModuleNum(Integer moduleNum);
}
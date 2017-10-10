package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;

import com.jumper.angel.hospital.doctor.entity.DoctorOrderInfo;

public interface DoctorOrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DoctorOrderInfo record);

    int insertSelective(DoctorOrderInfo record);

    DoctorOrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DoctorOrderInfo record);

    int updateByPrimaryKey(DoctorOrderInfo record);

	String selectServiceTypeByConsultantId(String consultantId);

	List<DoctorOrderInfo> findAll();
}
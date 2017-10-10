package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;

import com.jumper.angel.hospital.doctor.entity.HospitalDoctorMajorInfo;
/**
 * 医院科室相关Mapper
*
* HospitalDoctorMajorBeanMapper
*jumper-doctor
* 2016年12月22日 下午4:20:44
*
* @version 1.0.0
 */
public interface HospitalDoctorMajorInfoMapper{
	/** 根据id删除信息	 */
    int deleteByPrimaryKey(Integer id);
    /** 全字段插入	 */
    int insert(HospitalDoctorMajorInfo record);
    /** 非空字段插入，为空则不做处理 */
    int insertSelective(HospitalDoctorMajorInfo record);
    /** 根据id查询信息	 */
    HospitalDoctorMajorInfo selectByPrimaryKey(Integer id);
    /** 根据主键选择性更新，为空字段则不更新 */
    int updateByPrimaryKeySelective(HospitalDoctorMajorInfo record);
    /** 根据主键全部更新，对象某个字段为空，则会将数据库字段置空	 */
    int updateByPrimaryKey(HospitalDoctorMajorInfo record);
    /** 通过科室ID查询科室名称 */
	String findName(String major_id);
	/** 查询所有科室信息 */
	List<HospitalDoctorMajorInfo> findAll();
}
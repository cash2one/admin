package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;

import com.jumper.angel.hospital.doctor.entity.HospitalDoctorInfo;

/**
 * 医生信息相关Mapper
*
* HospitalDoctorInfoBeanMapper
*jumper-doctor
* 2016年12月22日 下午4:50:58
 */
public interface HospitalDoctorInfoMapper {
	/** 根据id删除信息	 */
    int deleteByPrimaryKey(Integer id);
    /** 全字段插入	 */
    int insert(HospitalDoctorInfo record);
    /** 非空字段插入，为空则不做处理 */
    int insertSelective(HospitalDoctorInfo record);
    /** 根据id查询信息	 */
    HospitalDoctorInfo selectByPrimaryKey(Integer id);
    /** 根据主键选择性更新，为空字段则不更新 */
    int updateByPrimaryKeySelective(HospitalDoctorInfo record);
    /** 根据主键全部更新，对象某个字段为空，则会将数据库字段置空	 */
    int updateByPrimaryKey(HospitalDoctorInfo record);
	String selectNameByPrimaryKey(Integer doctor_id);
	List<HospitalDoctorInfo> findAll();
	int selectByHospitalId(Integer id);
}
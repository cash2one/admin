package com.jumper.angel.hospital.hospital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.hospital.entity.HospitalDoctorMajor;

public interface HospitalDoctorMajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalDoctorMajor record);

    int insertSelective(HospitalDoctorMajor record);

    HospitalDoctorMajor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalDoctorMajor record);

    int updateByPrimaryKey(HospitalDoctorMajor record);
    /**查询总页数**/
	int findCount( @Param("keywords")String keywords);
	/**查询科室对象**/
	List<HospitalDoctorMajor> getHospitalDoctorMajor(@Param("beginIndex")int beginIndex, @Param("everyPage")int everyPage, @Param("keywords")String keywords);
	/**通过名称查询**/
	HospitalDoctorMajor getByMajor(@Param("major")String major);
}
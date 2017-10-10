package com.jumper.angel.hospital.hospital.mapper;

import java.util.List;

import com.jumper.angel.hospital.hospital.entity.HospitalServiceModule;

public interface HospitalServiceModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalServiceModule record);

    int insertSelective(HospitalServiceModule record);

    HospitalServiceModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalServiceModule record);

    int updateByPrimaryKey(HospitalServiceModule record);

    /**通过名称查询对象**/
	HospitalServiceModule selectByTitle(String title);

	/**查询前面12个默认功能**/
	List<HospitalServiceModule> selectByKey(Integer key);
}
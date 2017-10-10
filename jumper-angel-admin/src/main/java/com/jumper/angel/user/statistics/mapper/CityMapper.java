package com.jumper.angel.user.statistics.mapper;

import java.util.List;

import com.jumper.angel.user.statistics.entity.City;

/**
 * 城市信息mapper
 * @author gyx
 * @time 2017年3月14日
 */
public interface CityMapper {

	/**
	 * 通过省份获取城市列表
	 * @param id
	 * @return
	 */
	List<City> getCityByProvince(int id);
	
}

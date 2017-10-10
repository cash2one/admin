package com.jumper.angel.home.advertise.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.advertise.entity.PromotionAdvertisement;

/**
 * 广告mapper
 * @author gyx
 * @time 2017年2月7日
 */
public interface PromotionAdvertisementMapper {

	/**
	 * 条件获取广告总记录数
	 * @param map 关键词
	 * @return
	 */
	int findCount(Map<String, Object> map);

	/**
	 * 条件获取广告列表
	 * @param map
	 * @return
	 */
	List<PromotionAdvertisement> findAdvertiseList(Map<String, Object> map);

	/**
	 * 删除广告
	 * @param advertiseId 广告id
	 */
	void deleteAdvertise(int advertiseId);

	/**
	 * 添加广告信息
	 * @param advertise
	 */
	void addAdvertise(PromotionAdvertisement advertise);

	/**
	 * 修改广告信息
	 * @param advertise
	 */
	void updateAdvertise(PromotionAdvertisement advertise);
	
}

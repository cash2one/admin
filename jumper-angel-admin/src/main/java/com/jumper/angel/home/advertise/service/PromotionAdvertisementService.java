package com.jumper.angel.home.advertise.service;

import java.util.List;

import com.jumper.angel.home.advertise.entity.PromotionAdvertisement;

/**
 * 广告Service
 * @author gyx
 * @time 2017年2月7日
 */
public interface PromotionAdvertisementService {

	/**
	 * 条件获取广告总记录数
	 * @param keywords 关键词
	 * @return
	 */
	int findCount(String keywords);

	/**
	 * 
	 * @param beginIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param keywords 关键词
	 * @return
	 */
	List<PromotionAdvertisement> findAdvertiseList(int beginIndex,
			int everyPage, String keywords);

	/**
	 * 删除广告
	 * @param advertiseId 广告id
	 * @return
	 */
	boolean deleteAdvertise(int advertiseId);

	/**
	 * 添加或修改广告信息
	 * @param advertise
	 * @return
	 */
	boolean addOrEditAdvertise(PromotionAdvertisement advertise);
	
}

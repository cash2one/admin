package com.jumper.angel.home.advertise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.advertise.entity.PromotionAdvertisement;
import com.jumper.angel.home.advertise.mapper.PromotionAdvertisementMapper;
import com.jumper.angel.home.advertise.service.PromotionAdvertisementService;
import com.jumper.angel.utils.TimeUtils;

/**
 * 广告ServiceImpl
 * @author gyx
 * @time 2017年2月7日
 */
@Service
public class PromotionAdvertisementServiceImpl implements PromotionAdvertisementService{

	@Autowired
	private PromotionAdvertisementMapper advertiseMapper;
	
	/**
	 * 条件获取广告总记录数
	 */
	@Override
	public int findCount(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = advertiseMapper.findCount(map);
		return count;
	}
	
	/**
	 * 获取广告列表
	 */
	@Override
	public List<PromotionAdvertisement> findAdvertiseList(int beginIndex,
			int everyPage, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("keywords", keywords);
		List<PromotionAdvertisement> advertiseList = advertiseMapper.findAdvertiseList(map);
		if(advertiseList != null && advertiseList.size() > 0){
			return advertiseList;
		}
		return null;
	}

	/**
	 * 删除广告
	 */
	@Override
	public boolean deleteAdvertise(int advertiseId) {
		boolean b = false;
		try {
			advertiseMapper.deleteAdvertise(advertiseId);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	/**
	 * 添加或修改广告信息
	 */
	@Override
	public boolean addOrEditAdvertise(PromotionAdvertisement advertise) {
		boolean b = false;
		try {
			if(advertise.getId() == 0){
				//添加
				advertise.setAddTime(TimeUtils.getCurrentTime());
				advertise.setDocPush(0);
				advertise.setHospitalId(49);
				advertise.setIsBanner(0);
				advertise.setIsPush(0);
				advertiseMapper.addAdvertise(advertise);
			}else{
				//修改
				advertiseMapper.updateAdvertise(advertise);
			}
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}
	
	
	
}

package com.jumper.angel.hospital.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.business.entity.Device;
import com.jumper.angel.hospital.business.mapper.BusinessMapper;
import com.jumper.angel.hospital.business.mapper.DeviceMapper;
import com.jumper.angel.hospital.business.service.BusinessService;
/**
 * 商户信息
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	private BusinessMapper businessMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;

	/**
	 * 查询所有商户信息
	 * @version 1.0
	 * @createTime 2017年3月14日,上午11:37:11
	 * @updateTime 2017年3月14日,上午11:37:11
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessName 商户名字
	 * @param beginIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @return
	 */
	public List<Map<String, Object>> findAllBusiness(String businessName, int beginIndex, int everyPage) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessName", businessName);
		param.put("beginIndex", beginIndex);
		param.put("everyPage", everyPage);
		return businessMapper.findAllBusiness(param);
	}
	
	/**
	 * 查询商户总记录数
	 * @version 1.0
	 * @createTime 2017年3月14日,上午11:34:08
	 * @updateTime 2017年3月14日,上午11:34:08
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessName 商户名字
	 * @return
	 */
	public int findCount(String businessName) {
		return businessMapper.findCount(businessName);
	}
	
	/**
	 * 新增
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:17:11
	 * @updateTime 2017年3月14日,下午3:17:11
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param device
	 */
	public void insert(Device device) {
		deviceMapper.insert(device);
	}
	
	/**
	 * 查询商户设备是否存在
	 * @version 1.0
	 * @createTime 2017年3月17日,上午10:00:52
	 * @updateTime 2017年3月17日,上午10:00:52
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessId
	 * @param seriesNumber
	 * @return
	 */
	public boolean findBusinessDeviceBySeriesNumber(String businessId, String seriesNumber) {
		//参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessId", businessId);
		param.put("seriesNumber", seriesNumber);
		//通过序列号查询商户设备
		List<Device> list = deviceMapper.findBusinessDeviceBySeriesNumber(param);
		if(list!=null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询商户设备是否存在
	 * @version 1.0
	 * @createTime 2017年3月17日,上午10:00:52
	 * @updateTime 2017年3月17日,上午10:00:52
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessId
	 * @param mac
	 * @return
	 */
	public boolean findBusinessDeviceByMAC(String businessId, String mac) {
		//参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessId", businessId);
		param.put("mac", mac);
		//通过mac查询商户设备
		List<Device> list = deviceMapper.findBusinessDeviceByMAC(param);
		if(list!=null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询商户设备信息
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:17:45
	 * @updateTime 2017年3月14日,下午3:17:45
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessId 商户ID
	 * @param mac mac地址
	 * @param deviceType 设备类型
	 * @param beginIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @return
	 */
	public List<Device> findBusinessDevice(String businessId, String mac, int deviceType, int beginIndex, int everyPage) {
		//参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessId", businessId);
		param.put("mac", mac);
		param.put("deviceType", deviceType);
		param.put("beginIndex", beginIndex);
		param.put("everyPage", everyPage);
		return deviceMapper.findBusinessDevice(param);
	}
	
	/**
	 * 查询商户设备总记录数
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:18:18
	 * @updateTime 2017年3月14日,下午3:18:18
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessId 商户ID
	 * @param mac mac地址
	 * @param deviceType 设备类型
	 * @return
	 */
	public int findDeviceCount(String businessId, String mac, int deviceType) {
		//参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessId", businessId);
		param.put("mac", mac);
		param.put("deviceType", deviceType);
		return deviceMapper.findDeviceCount(param);
	}
	
	/**
	 * 删除设备
	 * @version 1.0
	 * @createTime 2017年3月14日,下午5:07:50
	 * @updateTime 2017年3月14日,下午5:07:50
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id 设备ID
	 */
	public void deleteDevice(long id) {
		deviceMapper.deleteDevice(id);
	}
}

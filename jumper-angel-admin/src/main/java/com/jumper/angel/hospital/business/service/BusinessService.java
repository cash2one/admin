package com.jumper.angel.hospital.business.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.hospital.business.entity.Device;

/**
 * 商户信息
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface BusinessService {
	
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
	public List<Map<String, Object>> findAllBusiness(String businessName, int beginIndex, int everyPage);
	
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
	public int findCount(String businessName);
	
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
	public void insert(Device device);
	
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
	public List<Device> findBusinessDevice(String businessId, String mac, int deviceType, int beginIndex, int everyPage);
	
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
	public int findDeviceCount(String businessId, String mac, int deviceType);
	
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
	public void deleteDevice(long id);
	
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
	public boolean findBusinessDeviceBySeriesNumber(String businessId, String seriesNumber);
	
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
	public boolean findBusinessDeviceByMAC(String businessId, String mac);
}

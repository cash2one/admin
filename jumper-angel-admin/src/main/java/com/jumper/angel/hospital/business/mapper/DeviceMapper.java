package com.jumper.angel.hospital.business.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.hospital.business.entity.Device;

/**
 * 设备信息
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface DeviceMapper {
	
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
	 * @param param
	 * @return
	 */
	public List<Device> findBusinessDevice(Map<String, Object> param);
	
	/**
	 * 查询商户设备总记录数
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:18:18
	 * @updateTime 2017年3月14日,下午3:18:18
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public int findDeviceCount(Map<String, Object> param);
	
	/**
	 * 通过序列号查询商户设备
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:18:51
	 * @updateTime 2017年3月14日,下午3:18:51
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public List<Device> findBusinessDeviceBySeriesNumber(Map<String, Object> param);
	
	/**
	 * 通过MAC查询商户设备
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:18:51
	 * @updateTime 2017年3月14日,下午3:18:51
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public List<Device> findBusinessDeviceByMAC(Map<String, Object> param);
	
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
}

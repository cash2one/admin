package com.jumper.angel.hospital.business.mapper;

import java.util.List;
import java.util.Map;

/**
 * 商户信息
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface BusinessMapper {
	
	/**
	 * 查询所有商户信息
	 * @version 1.0
	 * @createTime 2017年3月14日,上午11:33:37
	 * @updateTime 2017年3月14日,上午11:33:37
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> findAllBusiness(Map<String, Object> param);
	
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
}

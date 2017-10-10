package com.jumper.angel.home.health.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.health.entity.PregnancyInfo;

/**
 * 宝宝发育变化
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-23
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface PregnancyInfoMapper {
	
	/**
	 * 查询所有孕期信息
	 * @version 1.0
	 * @createTime 2016-12-23,下午6:03:37
	 * @updateTime 2016-12-23,下午6:03:37
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public List<PregnancyInfo> findAllPrepnancyInfo(Map<String, Object> param);
	
	/**
	 * 查询孕期总记录数
	 * @version 1.0
	 * @createTime 2016-12-26,上午10:31:29
	 * @updateTime 2016-12-26,上午10:31:29
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public int findCount();
	
	/**
	 * 新增孕期信息
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:41:18
	 * @updateTime 2016-12-26,下午5:41:18
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 */
	public void savePrepnancy(PregnancyInfo param);
	
	/**
	 * 通过孕周查询孕期信息
	 * @version 1.0
	 * @createTime 2016-12-27,上午9:49:03
	 * @updateTime 2016-12-27,上午9:49:03
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param week
	 * @return
	 */
	public PregnancyInfo findPrepnancyByWeek(int week);
	
	/**
	 * 更新孕期信息
	 * @version 1.0
	 * @createTime 2016-12-27,上午9:49:35
	 * @updateTime 2016-12-27,上午9:49:35
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 */
	public void updatePrepnancy(PregnancyInfo param);
	
	/**
	 * 删除孕期信息
	 * @version 1.0
	 * @createTime 2016-12-27,下午4:18:17
	 * @updateTime 2016-12-27,下午4:18:17
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param week
	 */
	public void deletePrepnancy(int week);
}

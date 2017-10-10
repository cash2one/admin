package com.jumper.angel.user.statistics.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.user.statistics.vo.VOUserDetailInfo;
import com.jumper.angel.user.statistics.vo.VOUserListInfo;

/**
 * 用户信息mapper
 * @author gyx
 * @time 2017年3月15日
 */
public interface UserInfoMapper {

	/**
	 * 获取满足查询条件的初次关联某医院的用户数据总数
	 * @param map
	 * @return
	 */
	int findCount(Map<String, Object> map);

	/**
	 * 获取满足查询条件的初次关联某医院的用户数据
	 * @param map
	 * @return
	 */
	List<VOUserListInfo> findHospitalDetailStatistics(Map<String, Object> map);

	/**
	 * 查询用户详情
	 * @param userId 用户id
	 * @return
	 */
	VOUserDetailInfo findUserInfoDetail(int userId);

}

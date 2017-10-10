package com.jumper.angel.user.statistics.service;

import java.text.ParseException;
import java.util.List;

import com.jumper.angel.user.statistics.vo.VOUserDetailInfo;
import com.jumper.angel.user.statistics.vo.VOUserListInfo;

/**
 * 用户信息service
 * @author gyx
 * @time 2017年3月15日
 */
public interface UserInfoService {

	/**
	 * 获取满足查询条件的初次关联某医院的用户数据
	 * @param id 医院id
	 * @param startTime 开始时间
	 * @param endTime 截止时间
	 * @param currentIdentity 用户状态
	 * @return
	 */
	int findCount(Integer id, String startTime, String endTime,
			int currentIdentity);

	/**
	 * 
	 * @param beginIndex 分页索引
	 * @param everyPage 每页条数
	 * @param id 医院id
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param currentIdentity 用户状态
	 * @return
	 */
	List<VOUserListInfo> findHospitalDetailStatistics(int beginIndex,
			int everyPage, Integer id, String startTime, String endTime,
			int currentIdentity);

	/**
	 * 获取用户详情
	 * @param userId 用户id
	 * @return
	 * @throws ParseException 
	 */
	VOUserDetailInfo findUserInfoDetail(int userId) throws ParseException;

}

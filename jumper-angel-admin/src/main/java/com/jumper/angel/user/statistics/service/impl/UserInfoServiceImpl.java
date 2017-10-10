package com.jumper.angel.user.statistics.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.user.statistics.mapper.UserInfoMapper;
import com.jumper.angel.user.statistics.service.UserInfoService;
import com.jumper.angel.user.statistics.vo.VOUserDetailInfo;
import com.jumper.angel.user.statistics.vo.VOUserListInfo;
import com.jumper.angel.utils.TimeUtils;

/**
 * 用户信息serviceImpl
 * @author gyx
 * @time 2017年3月15日
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	

	/**
	 * 获取满足查询条件的初次关联某医院的用户数据总数
	 */
	@Override
	public int findCount(Integer id, String startTime, String endTime,
			int currentIdentity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospitalId", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("currentIdentity", currentIdentity);
		int count = userInfoMapper.findCount(map);
		return count;
	}


	/**
	 * 获取满足查询条件的初次关联某医院的用户数据
	 */
	@Override
	public List<VOUserListInfo> findHospitalDetailStatistics(int beginIndex,
			int everyPage, Integer id, String startTime, String endTime,
			int currentIdentity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("hospitalId", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("currentIdentity", currentIdentity);
		List<VOUserListInfo> voUserInfoList =  userInfoMapper.findHospitalDetailStatistics(map);
		if(voUserInfoList != null && voUserInfoList.size() > 0){
			return voUserInfoList;
		}
		return null;
	}

	/**
	 * 获取用户详情
	 * @throws ParseException 
	 */
	@Override
	public VOUserDetailInfo findUserInfoDetail(int userId) throws ParseException {
		VOUserDetailInfo voUserDetailInfo = userInfoMapper.findUserInfoDetail(userId);
		//怀孕中
		if(voUserDetailInfo.getCurrentIdentity()==0){
			//怀孕状态
			voUserDetailInfo.setPregnantStatus("怀孕中");
			//孕周
			int week[] = TimeUtils.getPregnantWeek(TimeUtils.convertToDate(voUserDetailInfo.getExpectedDateOfConfinement()), new Date());
			String pregnantWeek = "孕"+week[0]+"周"+week[1]+"天";
			voUserDetailInfo.setPregnantWeek(pregnantWeek);
			//宝宝生日
			voUserDetailInfo.setBabyBirthDay("");
		}else if(voUserDetailInfo.getCurrentIdentity()==1){
			voUserDetailInfo.setPregnantStatus("已有宝宝");
			voUserDetailInfo.setPregnantWeek("");
			voUserDetailInfo.setExpectedDateOfConfinement("");
		}
		//初次关联操作设备
		voUserDetailInfo.setFirstBindMachine(voUserDetailInfo.getMobileType()==0?"Android":"iOS");
		
		return voUserDetailInfo;
	}

}
